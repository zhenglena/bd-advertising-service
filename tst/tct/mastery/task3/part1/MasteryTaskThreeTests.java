package tct.mastery.task3.part1;

import com.amazon.ata.advertising.service.helper.AdvertisingIntegrationTestBase;

import com.amazon.ata.advertising.service.mastery.task3.MasteryTaskThreeHelper;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.AdvertisingContent;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.DoubleStream;

import static com.amazon.ata.advertising.service.mastery.task3.MasteryTaskThreeHelper.assertContent;

public class MasteryTaskThreeTests extends AdvertisingIntegrationTestBase {
    private MasteryTaskThreeHelper helper;
    private List<String> contentToCleanup;

    /**
     * Ensure the test infra is ready for test run, including creating the client.
     */
    @BeforeClass
    public void setup() throws Exception {
        super.setup();
        helper = new MasteryTaskThreeHelper(advertisementServiceClient);
        contentToCleanup = new ArrayList<>();
    }

    @AfterClass
    public void cleanup() {
        contentToCleanup.forEach(helper::deleteContent);
    }

    @Test
    public void generateAdvertisement_multipleContentEachSingleTargetingGroups_alwaysReturnsHighestCTR() {
        // GIVEN
        String marketplaceId = UUID.randomUUID().toString();
        String customerId = "123";
        DoubleStream.iterate(0, x -> x + 0.3)
                .limit(3)
                .mapToObj(ctr -> helper.createContent(marketplaceId, ctr))
                .map(AdvertisingContent::getId)
                .forEach(contentToCleanup::add);
        AdvertisingContent expectedContent = helper.createContent(marketplaceId, 1.0);
        contentToCleanup.add(expectedContent.getId());
        GenerateAdvertisementRequest request = MasteryTaskThreeHelper.createGenAdRequest(marketplaceId, customerId);

        // WHEN
        List<GenerateAdvertisementResult> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(advertisementServiceClient.generateAdvertisement(request));
        }

        // THEN
        results.forEach(result ->
            assertContent(expectedContent.getContent(), result, Arrays.asList(0.0, 0.3, 0.6, 1.0)));
    }
}
