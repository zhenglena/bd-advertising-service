package tct.mastery.task2;

import com.amazon.ata.advertising.service.helper.AdvertisingIntegrationTestBase;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementResult;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.amazon.ata.advertising.service.helper.TestConstants.PARENT_PROFILE_CUSTOMER_ID;
import static com.amazon.ata.advertising.service.helper.TestConstants.US_MARKETPLACE_ID;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Participants update their stream implementation to execute all predicates concurrently
 */
public class MasteryTaskTwoTests extends AdvertisingIntegrationTestBase {
    private static final Logger LOG = LogManager.getLogger(MasteryTaskTwoTests.class);

    private static final int HARDCODED_SLEEP_DURATION = 200;
    // Empirical testing indicates this number is likely 88. Logging indicates 89 predicates.
    private static final int NUMBER_OF_PREDICATES = 85;
    // Empirical testing indicates this number is close to 55. Logging indicates 29 targeting groups.
    private static final int NUMBER_OF_TARGETING_GROUPS = 55;
    private static final int CONCURRENT_APPROXIMATE_EXECUTION_DURATION =
        HARDCODED_SLEEP_DURATION * NUMBER_OF_TARGETING_GROUPS;
    private static final int SERIAL_MINIMUM_EXECUTION_DURATION = HARDCODED_SLEEP_DURATION * NUMBER_OF_PREDICATES;

    @BeforeClass
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void generateAdvertisement_withTargetCustomerIdInMarketplace_returnsAdvertisement() {
        GenerateAdvertisementRequest request = new GenerateAdvertisementRequest()
            .withCustomerId(PARENT_PROFILE_CUSTOMER_ID)
            .withMarketplaceId(US_MARKETPLACE_ID);

        long start = System.currentTimeMillis();
        GenerateAdvertisementResult result = super.advertisementServiceClient.generateAdvertisement(request);
        long finish = System.currentTimeMillis();

        long elapsed = finish - start;
        LOG.info("GenerateAdvertisement request took {} milliseconds to complete, " +
                    "expected less than {} ms. GenerateAdvertisement should " +
                    "evaluate predicates concurrently!",
                elapsed, SERIAL_MINIMUM_EXECUTION_DURATION);

        assertNotNull(result.getAdvertisement(), "Expected a non null advertisement in the response.");
        assertNotNull(result.getAdvertisement().getId(), "Expected the advertisement to have a non-null " +
            "content ID.");
        assertFalse(StringUtils.isBlank(result.getAdvertisement().getContent()), "Expected a non-empty " +
            "advertisement content when generating an advertisement for a customer ID with a parent profile " +
            "in marketplace ID: " + request.getMarketplaceId());
    }
}
