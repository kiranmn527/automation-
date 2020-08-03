package com.cucumber.testng.configs.project_configs;

import com.cucumber.testng.project_constants.constants.Settings;
import com.cucumber.testng.project_constants.enams.BrowserType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {


    public static void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    private void ReadProperty() throws IOException {
        //Create Property Object
        Properties p = new Properties();
        //Load the Property file available in same package
        InputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\cucumber\\testng\\configs\\project_configs\\" + "GlobalConfig.properties");
        p.load(inputStream);
        //Get DriverType
        Settings.DriverType = p.getProperty("DriverType");
        //GEt ExcelSheetPath
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        //Browser Type
        Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
        //Hub
        Settings.SeleniumGridHub = p.getProperty("SeleniumGrid");
        //Historical Report
        Settings.HistoricalReport = p.getProperty("HistoricalReport");
        //Application URL
        Settings.AUT = p.getProperty("AUT");

        //API constants
        Settings.TwitterBaseURI = p.getProperty("TwitterBaseURI");
        Settings.TwitterBasePath = p.getProperty("TwitterBasePath");
        Settings.ConsumerKey = p.getProperty("ConsumerKey");
        Settings.ConsumerSecret = p.getProperty("ConsumerSecret");
        Settings.Token = p.getProperty("Token");
        Settings.TokenSecrete = p.getProperty("TokenSecrete");
    }
}
