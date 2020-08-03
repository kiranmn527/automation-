package com.cucumber.testng.page_objects.web;

import org.openqa.selenium.WebDriver;

public class WebBasePage {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<WebDriver>();

    public static synchronized void setDriver(WebDriver driver){
        drivers.set(driver);
    }

    public static synchronized WebDriver getDriver(){
        return drivers.get();
    }
}
