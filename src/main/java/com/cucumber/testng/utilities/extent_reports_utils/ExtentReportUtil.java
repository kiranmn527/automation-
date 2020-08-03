package com.cucumber.testng.utilities.extent_reports_utils;


import io.cucumber.core.api.Scenario;

import java.io.File;

public class ExtentReportUtil {

    private static ThreadLocal<Scenario> scenarios = new ThreadLocal<Scenario>();
    public static final String REPORT_PATH = System.getProperty("user.dir") + File.separator + "reports" + File.separator;

    public static synchronized void setScenario(Scenario scenario) {
        scenarios.set(scenario);
    }

    public static synchronized Scenario getScenarios() {
        return scenarios.get();
    }

    public static void log(String str) {
        getScenarios().write(str);
    }
}
