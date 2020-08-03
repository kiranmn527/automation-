package com.cucumber.testng.runner.testng_listeners;

import com.cucumber.testng.configs.project_configs.ConfigReader;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestNgListener implements IInvokedMethodListener {
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        if (!iInvokedMethod.isTestMethod()) {
            //Initialize Config
            try {
                ConfigReader.PopulateSettings();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
