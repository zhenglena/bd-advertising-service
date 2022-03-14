package tct.mastery.task3;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.ATACurriculumAdvertisingServiceLambda;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.Advertisement;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.AdvertisingContent;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.CreateContentRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.CreateContentResult;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.DeleteContentRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementResult;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.TargetingGroup;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.UpdateClickThroughRateRequest;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class MasteryTaskThreeHelper {
    private ATACurriculumAdvertisingServiceLambda advertisementServiceClient;

    public MasteryTaskThreeHelper(ATACurriculumAdvertisingServiceLambda advertisementServiceClient) {
        this.advertisementServiceClient = advertisementServiceClient;
    }

    public static GenerateAdvertisementRequest createGenAdRequest(String marketplaceId) {
        return createGenAdRequest(marketplaceId, null);
    }

    public static GenerateAdvertisementRequest createGenAdRequest(String marketplaceId, String customerId) {
        return new GenerateAdvertisementRequest()
                .withMarketplaceId(marketplaceId)
                .withCustomerId(customerId);
    }

    public static void assertContent(String expectedContent, GenerateAdvertisementResult result,
                                     List<Double> eligibleCTRs) {
        Advertisement advertisement = result.getAdvertisement();
        String actualContent = advertisement.getContent();
        assertEquals(String.format("Did not select the advertisement with the highest CTR. " +
                "The CTRs of eligible ads available were: %s", eligibleCTRs), expectedContent, actualContent);
    }

    public AdvertisingContent createContent(String marketplaceId, double clickThroughRate) {
        String content = UUID.randomUUID().toString();
        CreateContentRequest request = new CreateContentRequest().withMarketplaceId(marketplaceId).withContent(content);
        CreateContentResult result = advertisementServiceClient.createContent(request);

        updateCTR(result.getTargetingGroup(), clickThroughRate);

        return result.getAdvertisingContent();
    }

    public void updateCTR(TargetingGroup targetingGroup, double clickThroughRate) {
        UpdateClickThroughRateRequest updateClickThroughRateRequest = new UpdateClickThroughRateRequest()
                .withTargetingGroupId(targetingGroup.getTargetingGroupId())
                .withClickThroughRate(clickThroughRate);
        advertisementServiceClient.updateClickThroughRate(updateClickThroughRateRequest);
    }

    public void deleteContent(String contentId) {
        DeleteContentRequest request = new DeleteContentRequest().withContentId(contentId);
        advertisementServiceClient.deleteContent(request);
    }
}
