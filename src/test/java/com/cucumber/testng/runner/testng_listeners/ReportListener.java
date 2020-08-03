package com.cucumber.testng.runner.testng_listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.cucumber.testng.page_objects.web.WebBasePage.getDriver;
import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.getScenarios;
import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.log;

public class ReportListener implements ITestListener {
    @Override
    public synchronized void onTestStart(ITestResult iTestResult) {
        System.out.println("On Test Start");
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On Test Sucess");
    }

    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        try {
            if (getDriver() != null) {
                embedScreenshot();
                getScenarios().write("URL: " + getDriver().getCurrentUrl());
            }
        } catch (WebDriverException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On Test Skipped");
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println(" Test Percentage");
    }

    @Override
    public synchronized void onStart(ITestContext iTestContext) {
        System.out.println("On Start");
    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {
        System.out.println("On Test Finish");
    }

    private void embedScreenshot() {
        String base64Img = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        log("<img src=\"data:image/png;base64," + base64Img + "\" ></img>");
    }
}
