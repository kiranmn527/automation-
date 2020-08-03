package com.cucumber.testng.runner;

import com.cucumber.testng.runner.testng_listeners.BaseRunner;
import io.cucumber.testng.*;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cucumber.testng.stepdef"},
        tags = {"not @Sequential"},
        plugin = {"pretty", "html:target/cucumber-report/single",
                "json:target/cucumber-report/single/cucumber.json",
                "rerun:target/cucumber-report/single/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class ParallelRunner extends BaseRunner {
    private io.cucumber.testng.TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(ParallelRunner.class);
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios
     * with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }
}
