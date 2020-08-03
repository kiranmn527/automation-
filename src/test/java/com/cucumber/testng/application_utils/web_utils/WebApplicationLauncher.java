package com.cucumber.testng.application_utils.web_utils;

import com.cucumber.testng.page_objects.web.LoginPage;
import com.cucumber.testng.project_constants.constants.Settings;

import static com.cucumber.testng.page_objects.web.WebBasePage.getDriver;
import static java.lang.System.getProperty;

public class WebApplicationLauncher {

    public static LoginPage launchAUT() {
        getDriver().get(Settings.AUT);
        LoginPage loginPage = new LoginPage();
        return loginPage;
    }
}
