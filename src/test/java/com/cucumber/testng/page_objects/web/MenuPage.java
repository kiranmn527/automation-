package com.cucumber.testng.page_objects.web;

import org.openqa.selenium.By;

import static com.cucumber.testng.application_utils.web_utils.WebObjects.*;
import static com.cucumber.testng.project_constants.enams.Frame.MENU;
import static com.cucumber.testng.utilities.extent_reports_utils.ExtentReportUtil.log;

public class MenuPage extends WebBasePage {

    private By LOGOUT_LINK = By.xpath("//*[@id=\"cssmenu\"]/ul/li[1]/a/span");
    private By BOX_MGMT_LINK = By.xpath("//a[contains(text(),'Box Mgmt')]");
    private By INITIAL  = By.id("Initial");
    private By INITIALFail  = By.id("InitialFail");

    public LoginPage logout() {
        System.out.println("Logging Out");
        switchToFrame(MENU);
        click(LOGOUT_LINK);
        return new LoginPage();
    }


    public boolean verifyIfLoggedInSuccessfully() {
        sleep(500);
//        switchToFrame(MENU);
        return (getElementCount(INITIAL) != 0);
    }
    public boolean verifyIfLoggedFailed() {
        sleep(500);
//        switchToFrame(MENU);
        return (getElementCount(INITIALFail) != 0);
    }


    public BoxPage gotoBoxManagement() {
        log("In Menu page");
        openSection("Scanning");
        click(BOX_MGMT_LINK);
        return new BoxPage();
    }

    public void openSection(String section) {
        switchToFrame(MENU);
        if (getElementCount(By.xpath("//ul[@id='menu']/h3/a[text()='" + section + "']/preceding-sibling::span[contains(@class, 'icon-triangle-1-e')]")) == 1) {
            waitForPageToLoad();
            sleep(2000);
            forceClick(By.xpath("//ul[@id='menu']/h3/a[text()='" + section + "']"));
        }
    }
}
