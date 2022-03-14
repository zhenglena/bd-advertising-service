package tct.mastery.task1.loops;

import com.amazon.ata.advertising.service.helper.TctIntrospectionTest;

import com.amazonaws.services.atacurriculumadvertisingservicelambda.model.TctResult;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MasteryTaskOneLoopTests{//} extends TctIntrospectionTest {
    private static final String TEST_SUITE_ID = "MT01-Loop";

    //@Override
    protected String getTestSuiteId() {
        return TEST_SUITE_ID;
    }

    @Test(dataProvider = "TctResults")
    public void masteryTaskOneMilestoneOne_runIntrospectionSuite_reportResults(TctResult result) {
        assertTrue(result.isPassed(), result.getErrorMessage());
    }
}
