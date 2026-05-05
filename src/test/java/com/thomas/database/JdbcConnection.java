package com.thomas.database;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnection {
    private static final Logger logger = Logger.getLogger(JdbcConnection.class.getName());

    public static void main(String[] args) {
        String scenario = "zeroBalanceCard";
        String[] credentials = DatabaseUtil.getCredentials(scenario);

        if (credentials[0] == null || credentials[1] == null) {
            logger.severe("Credentials not found for scenario: " + scenario);
            return;
        }

        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://login.salesforce.com/");
            driver.manage().window().maximize();

            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
            usernameField.sendKeys(credentials[0]);

            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            passwordField.sendKeys(credentials[1]);

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("Login")));
            loginButton.click();

            // Optionally, wait for some element to confirm login
            // wait.until(ExpectedConditions.urlContains("home"));

            logger.info("Login attempt completed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login test", e);
        } finally {
            driver.quit();
        }
    }
}
