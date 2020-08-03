package com.cucumber.testng.page_objects.web;

import com.cucumber.testng.model.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.cucumber.testng.application_utils.web_utils.WebObjects.*;

public class LoginPage extends WebBasePage {

    private By USERNAME_INPUT = By.name("UserName");
    private By PASSWORD_INPUT = By.name("Password");
    private By LOGIN_FAILED_ERROR_TEXT = By.xpath("//div[@style='display: block;' and text()='Logon Failed']");
    public By LOGIN_BTN = By.xpath("/html/body/form/p[3]/input");

    public MenuPage performLogin(LoginCredentials credentials) {
        System.out.println("Clicking login button");
        getDriver().switchTo().defaultContent();
        input(USERNAME_INPUT, credentials.getUsername());
        input(PASSWORD_INPUT, credentials.getPassword());
        click(LOGIN_BTN);
//        keyPress(LOGIN_BTN, Keys.ENTER);
        return new MenuPage();
    }
}
