package com.cucumber.testng.runner;

import com.cucumber.testng.runner.testng_listeners.BaseRunner;
import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.cucumber.testng.stepdef"},
        tags = {"@Debug"},
        plugin = {"pretty", "html:target/cucumber-report/single",
                "json:target/cucumber-report/single/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class DebugRunner extends BaseRunner {
}
