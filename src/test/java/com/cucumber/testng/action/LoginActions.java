package com.cucumber.testng.action;

import com.cucumber.testng.model.LoginCredentials;
import com.cucumber.testng.page_objects.web.BoxPage;
import com.cucumber.testng.page_objects.web.LoginPage;
import com.cucumber.testng.page_objects.web.MenuPage;
import com.cucumber.testng.project_constants.constants.Settings;
import com.cucumber.testng.application_utils.web_utils.WebDriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.cucumber.testng.application_utils.web_utils.WebApplicationLauncher.launchAUT;
import static com.cucumber.testng.application_utils.web_utils.WebObjects.getElement;
import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.log;

public class LoginActions {
    private LoginPage loginPage;
    private MenuPage menupage;
    private BoxActions boxActions;
    private BoxPage boxPage;

    public LoginActions(LoginPage loginPage, MenuPage menupage, BoxActions boxActions, BoxPage boxPage){
        this.loginPage = loginPage;
        this.menupage = menupage;
        this.boxActions = boxActions;
        this.boxPage = boxPage;
    }

    public void launchApplication(){
        System.out.println("launchApplication");
        WebDriverFactory.createDriverInstance(Settings.BrowserType);
        loginPage = launchAUT();
    }

    public void performLogin(LoginCredentials credentials){
        menupage = loginPage.performLogin(credentials);
        //Assert.assertEquals("Its not displayed", getElement(By.id("Initial")).isDisplayed(), true);

    }

    public boolean isLogoutLinkVisible(){
        System.out.println("Checking for Logout link");
        return menupage.verifyIfLoggedInSuccessfully();
    }


    public boolean isNOtLogoutLinkVisible(){
        System.out.println("Checking for Logout link");
        return menupage.verifyIfLoggedFailed();
    }

    public void dummyMethod() {
        log("I am in Dummy method");
        System.out.println("Box name in Login Action class got from Box Action "+boxActions.getBoxNameFromBoxAction());
        System.out.println("Box name in Login Action class got from Box Page "+ boxActions.getBoxPage().getBoxNameFromBoxPage());
    }
}
