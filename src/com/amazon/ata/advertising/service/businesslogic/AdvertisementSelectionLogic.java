package com.amazon.ata.advertising.service.businesslogic;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.EmptyGeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.advertising.service.targeting.TargetingEvaluator;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import javax.inject.Inject;

/**
 * This class is responsible for picking the advertisement to be rendered.
 */
public class AdvertisementSelectionLogic {

    private static final Logger LOG = LogManager.getLogger(AdvertisementSelectionLogic.class);

    private final ReadableDao<String, List<AdvertisementContent>> contentDao;
    private final ReadableDao<String, List<TargetingGroup>> targetingGroupDao;
    private Random random = new Random();

    /**
     * Constructor for AdvertisementSelectionLogic.
     * @param contentDao Source of advertising content.
     * @param targetingGroupDao Source of targeting groups for each advertising content.
     */
    @Inject
    public AdvertisementSelectionLogic(ReadableDao<String, List<AdvertisementContent>> contentDao,
                                       ReadableDao<String, List<TargetingGroup>> targetingGroupDao) {
        this.contentDao = contentDao;
        this.targetingGroupDao = targetingGroupDao;
    }

    /**
     * Setter for Random class.
     * @param random generates random number used to select advertisements.
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * Gets all the content and metadata for the marketplace and determines which content can be shown.  Returns the
     * eligible content with the highest click-through rate.  If no advertisement is available or eligible, returns an
     * EmptyGeneratedAdvertisement.
     *
     * @param customerId - the customer to generate a custom advertisement for
     * @param marketplaceId - the id of the marketplace the advertisement will be rendered on
     * @return an advertisement customized for the customer id provided, or an empty advertisement if one could
     *     not be generated.
     */
    public GeneratedAdvertisement selectAdvertisement(String customerId, String marketplaceId) {
        GeneratedAdvertisement generatedAdvertisement = new EmptyGeneratedAdvertisement();
        if (StringUtils.isEmpty(marketplaceId)) {
            LOG.warn("MarketplaceId cannot be null or empty. Returning empty ad.");
            return generatedAdvertisement;
        }
        //MT1 && MT3
        Map<Double, AdvertisementContent> eligibleAdsByClickThruRate = new TreeMap<>(Comparator.reverseOrder());
        RequestContext context = new RequestContext(customerId, marketplaceId);
        TargetingEvaluator evaluator = new TargetingEvaluator(context);

        List<AdvertisementContent> validContents = findValidContents(contentDao.get(marketplaceId), evaluator);

        for (AdvertisementContent content : validContents) {
            Optional<Double> highest = targetingGroupDao.get(content.getContentId()).stream()
                    .map(TargetingGroup::getClickThroughRate)
                    .max(Double::compareTo);
            highest.ifPresent(aDouble -> eligibleAdsByClickThruRate.put(aDouble, content));
        }

        if (!eligibleAdsByClickThruRate.isEmpty()) {
            Optional<Double> highest = eligibleAdsByClickThruRate.keySet().stream().max(Double::compareTo);
            AdvertisementContent content = eligibleAdsByClickThruRate.get(highest.get());

            return new GeneratedAdvertisement(content);
        }
        //end
        return generatedAdvertisement;
    }

    //MT1
    /**
     * Sorts through a List of AdvertisementContent, where each contentID will be used to find a List of
     * TargetingGroups. Each List of TargetingGroups will be sorted (highest to lowest), then evaluated, then
     * filtered. If the resulting List of TargetingGroups is not empty, the contentID will be set to the highest
     * TargetingGroup contentID.
     * @param contents List of AdvertisementContent to be evaluated
     * @param evaluator the evaluator that takes into account the customerID
     * @return either an empty List or a list of valid AdvertisementContent objects.
     */
    private List<AdvertisementContent> findValidContents (List<AdvertisementContent> contents, TargetingEvaluator evaluator) {
        List<AdvertisementContent> returnList = new ArrayList<>();
        for (AdvertisementContent content : contents) {
            Optional<TargetingGroup> targetingGroup = targetingGroupDao.get(content.getContentId())
                    .stream()
                    .sorted(Comparator.comparing(TargetingGroup::getClickThroughRate).reversed()) //sort by click-thru rate
                    .filter(group -> evaluator.evaluate(group).isTrue()) //filtered out groups that aren't "true"
                    .findFirst(); //the highest click-rate targeting group
            if (targetingGroup.isPresent()) {
                returnList.add(content);
            }
        }
        return returnList;
    }
    //end
}
