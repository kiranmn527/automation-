package com.cucumber.testng.stepdef;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;

import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.setScenario;

public class ServiceHooks {

    @Before
    public void initializeTest(Scenario scenario) {
        setScenario(scenario);
    }
}
