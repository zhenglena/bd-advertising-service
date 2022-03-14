package tct.mastery.task2;

import com.amazon.ata.advertising.service.helper.TctIntrospectionTest;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.TctResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MasteryTaskTwoExecutorServiceTests extends TctIntrospectionTest {
    private static final String TEST_SUITE_ID = "MT02";

    @Override
    protected String getTestSuiteId() {
        return TEST_SUITE_ID;
    }

    @Test(dataProvider = "TctResults")
    public void masteryTaskTwo_runIntrospectionSuite_reportResults(TctResult result) {
        assertTrue(result.isPassed(), result.getErrorMessage());
    }
}
