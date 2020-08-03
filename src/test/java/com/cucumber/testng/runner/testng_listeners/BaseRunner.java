package com.cucumber.testng.runner.testng_listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;

import static com.cucumber.testng.application_utils.api_utils.APIHelper.getResponseWriterList;
import static com.cucumber.testng.application_utils.api_utils.APIHelper.isThisARestAPITest;
import static com.cucumber.testng.application_utils.web_utils.WebDriverFactory.closeDriver;
import static com.cucumber.testng.application_utils.web_utils.WebDriverFactory.isThisAWebTest;
import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.log;

public class BaseRunner extends AbstractTestNGCucumberTests {

    @AfterMethod
    public void tearDown() {
        if (isThisAWebTest()) {
            closeDriver();
        } else if (isThisARestAPITest()) {
            log(getResponseWriterList().toString());
        }
    }
}
