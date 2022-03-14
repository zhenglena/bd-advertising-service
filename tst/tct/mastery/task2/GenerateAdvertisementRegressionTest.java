package tct.mastery.task2;

import com.amazon.ata.advertising.service.helper.AdvertisingIntegrationTestBase;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementRequest;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.GenerateAdvertisementResult;
import org.apache.commons.lang.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.amazon.ata.advertising.service.helper.TestConstants.PARENT_PROFILE_CUSTOMER_ID;
import static com.amazon.ata.advertising.service.helper.TestConstants.US_MARKETPLACE_ID;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Participants make change to the dagger wiring of DAOs. This should ensure that everything still gets wired together
 * correctly.
 */
public class GenerateAdvertisementRegressionTest extends AdvertisingIntegrationTestBase {

    @BeforeClass
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void generateAdvertisement_withTargetCustomerIdInMarketplace_returnsAdvertisement() {
        GenerateAdvertisementRequest request = new GenerateAdvertisementRequest()
                .withCustomerId(PARENT_PROFILE_CUSTOMER_ID)
                .withMarketplaceId(US_MARKETPLACE_ID);
        GenerateAdvertisementResult result = super.advertisementServiceClient.generateAdvertisement(request);

        assertNotNull(result.getAdvertisement(), "Expected a non null advertisement in the response.");
        assertNotNull(result.getAdvertisement().getId(), "Expected the advertisement to have a non-null " +
                "content ID.");
        assertFalse(StringUtils.isBlank(result.getAdvertisement().getContent()), "Expected a non-empty " +
                "advertisement content when generating an advertisement for a customer ID with a parent profile " +
                "in marketplace ID: " + request.getMarketplaceId());
    }
}
