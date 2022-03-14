//package tct.helper;
//
//import com.amazon.ata.test.tct.TctResultExceptionIterator;
//
//import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.ExecuteTctSuiteRequest;
//import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.ExecuteTctSuiteResult;
//import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.TctSuiteReport;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//
//import java.util.Iterator;
//
///**
// * Class that contains a data provider to get introspection results via the ExecuteTctSuite API.
// * The getTestSuiteId method needs to be overriden so the data provider can call to get results
// * for the appropriate test suite.
// */
//public abstract class TctIntrospectionTest extends AdvertisingIntegrationTestBase {
//    /**
//     * Ensure the test infra is ready for test run, including creating the client.
//     * @throws Exception if the super class cannot setup correctly
//     */
//    @BeforeClass
//    public void setup() throws Exception {
//        super.setup();
//    }
//
//    /**
//     * Makes a call to the ExecuteTctSuite to run a test suite, which is determined by calling
//     * getTestSuiteId().
//     * @return a 2D Object Array - each Array has one object in it, a
//     *         {@link com.amazonaws.services.atacurriculumadvertisingservicelambda.model.TctResult} object
//     */
//    @DataProvider(name = "TctResults")
//    public Iterator<Object[]> getTctResults() {
//        try {
//            ExecuteTctSuiteRequest request = new ExecuteTctSuiteRequest().withTctSuiteId(getTestSuiteId());
//            ExecuteTctSuiteResult result = advertisementServiceClient.executeTctSuite(request);
//            TctSuiteReport report = result.getTctSuiteReport();
//
//            return report.getTctResultList()
//                    .stream()
//                    .map(tctResult -> new Object[]{tctResult})
//                    .iterator();
//        } catch (Exception e) {
//            return new TctResultExceptionIterator(e);
//        }
//    }
//
//    /**
//     * Returns the testSuiteId the test class would like to execute.
//     * @return the TestSuiteId for the test suite you would like to be run.
//     */
//    protected abstract String getTestSuiteId();
//}
