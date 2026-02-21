package com.thomas.web.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
