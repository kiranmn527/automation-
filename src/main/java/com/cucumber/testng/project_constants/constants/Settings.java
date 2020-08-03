package com.cucumber.testng.project_constants.constants;

import java.io.File;

public class Settings {
    //Log Path for framework
    public static String LogPath = System.getProperty("user.dir") + "\\target\\logs";
    //Driver Type for SQL Server connectivity
    public static String DriverType;
    public static String ExcelSheetPath;
    public static com.cucumber.testng.project_constants.enams.BrowserType BrowserType;
    public static String SeleniumGridHub;
    public static String HistoricalReport;
    public static String AUT;
    public static String TwitterBaseURI;
    public static String TwitterBasePath;
    public static String ConsumerKey;
    public static String ConsumerSecret;
    public static String Token;
    public static String TokenSecrete;
    public static String Project_directory = System.getProperty("user.dir");
    public static final String sep = File.separator;
}
