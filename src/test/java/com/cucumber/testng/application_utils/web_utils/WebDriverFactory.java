package com.cucumber.testng.application_utils.web_utils;

import com.cucumber.testng.project_constants.enams.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.cucumber.testng.page_objects.web.WebBasePage.getDriver;
import static com.cucumber.testng.page_objects.web.WebBasePage.setDriver;
import static com.cucumber.testng.project_constants.constants.Settings.Project_directory;

public class WebDriverFactory {

    public synchronized static void createDriverInstance(BrowserType browserType) {
        if(browserType.name().toLowerCase().contains("chrome")){
            System.setProperty("webdriver.chrome.driver", Project_directory+ "\\src\\main\\java\\com\\cucumber\\testng\\drivers\\web\\chromedriver.exe");
            WebDriver _driver = new ChromeDriver();
            _driver.manage().window().maximize();
            setDriver(_driver);
        }
    }

    public static void closeDriver(){
        getDriver().quit();
    }

    public static boolean isThisAWebTest() {
        try {
            String handle = getDriver().getWindowHandle();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
