package tct.mastery.task1.logic;

import com.amazon.ata.advertising.service.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.GenerateAdvertisementResult;
//import com.amazon.ata.advertising.service.helper.AdvertisingIntegrationTestBase;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static tct.helper.TestConstants.*;

public class MasteryTaskOneLogicTests {//extends AdvertisingIntegrationTestBase {

    /**
     * Ensure the test infra is ready for test run, including creating the client.
     */
    @BeforeClass
    public void setup() throws Exception {
        super.setup();
    }

    @Test
    public void generateAdvertisement_withTargetCustomerIdInMarketplace_returnsAdvertisement() {
        GenerateAdvertisementRequest request = GenerateAdvertisementRequest.builder()
            .withCustomerId(PARENT_PROFILE_CUSTOMER_ID)
            .withMarketplaceId(US_MARKETPLACE_ID)
            .build();
        GenerateAdvertisementResult result = super.advertisementServiceClient.generateAdvertisement(request);

        assertNotNull(result.getAdvertisement(), "Expected a non null advertisement in the response.");
        assertNotNull(result.getAdvertisement().getId(), "Expected the advertisement to have a non-null " +
            "content ID.");
        assertFalse(StringUtils.isBlank(result.getAdvertisement().getContent()), "Expected a non-empty " +
            "advertisement content when generating an advertisement for a customer ID with a parent profile " +
            "in marketplace ID: " + request.getMarketplaceId());
    }

    @Test
    public void generateAdvertisement_withTargetCustomerIdNotInMarketplace_returnsEmptyContent() {
        GenerateAdvertisementRequest request = new GenerateAdvertisementRequest()
            .withCustomerId(EMPTY_PROFILE_CUSTOMER_ID)
            .withMarketplaceId(CA_MARKETPLACE_ID);
        GenerateAdvertisementResult result = super.advertisementServiceClient.generateAdvertisement(request);

        assertNotNull(result.getAdvertisement(), "Expected a non null advertisement in the response.");
        assertNotNull(result.getAdvertisement().getId(), "Expected the advertisement to have a non-null " +
            "content ID.");
        assertTrue(StringUtils.isBlank(result.getAdvertisement().getContent()), "Expected an empty " +
            "advertisement content when generating an advertisement for a customer ID with an unknown profile " +
            "in marketplace ID: " + request.getMarketplaceId());
    }

    @Test
    public void generateAdvertisement_withNonExistantMarketplace_returnsEmptyContent() {
        GenerateAdvertisementRequest request = new GenerateAdvertisementRequest()
            .withCustomerId(EMPTY_PROFILE_CUSTOMER_ID)
            .withMarketplaceId("TCT_TESTS_MARKETPLACE_ID");
        GenerateAdvertisementResult result = super.advertisementServiceClient.generateAdvertisement(request);

        assertNotNull(result.getAdvertisement(), "Expected a non null advertisement in the response.");
        assertNotNull(result.getAdvertisement().getId(), "Expected the advertisement to have a non-null " +
            "content ID.");
        assertTrue(StringUtils.isBlank(result.getAdvertisement().getContent()), "Expected an empty " +
            "advertisement content when generating an advertisement for a customer ID with an unknown profile " +
            "in a non-existant marketplace ID: " + request.getMarketplaceId());
    }
}
