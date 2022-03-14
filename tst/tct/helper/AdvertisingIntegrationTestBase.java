package tct.helper;

import com.amazon.ata.integration.test.LambdaIntegrationTestBase;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.ATACurriculumAdvertisingServiceLambda;
import com.amazonaws.services.atacurriculumadvertisingservicelambda.ATACurriculumAdvertisingServiceLambdaClientBuilder;


/**
 * Configures the service client using the builder and the cloudformation stack name that should be used by all tests
 * for this project.
 */
public class AdvertisingIntegrationTestBase extends LambdaIntegrationTestBase {
    protected ATACurriculumAdvertisingServiceLambda advertisementServiceClient;

    /**
     * Configures the advertisingServiceClient to be used in the test class.
     * @throws Exception when the client cannot be created
     */
    public void setup() throws Exception {
        this.advertisementServiceClient = getServiceClient(
                ATACurriculumAdvertisingServiceLambdaClientBuilder.standard(),
                "ATACurriculumAdvertisingServiceLambda");
    }

}
