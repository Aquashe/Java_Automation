package com.thomas.web.practice.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.time.Duration;

public class IntroTwo {
    public  static void  main(String [] args) throws InterruptedException {
        String username = "rahul";

        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



        driver.findElement(By.cssSelector("#inputUsername")).sendKeys(username);
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys(getPassword(driver));
        Thread.sleep(2000);
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.cssSelector("button[class='submit signInBtn']")).click();

        Thread.sleep(2000);

        System.out.println(driver.findElement(By.tagName("p")).getText());
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
        Assert.assertEquals(driver.findElement(By.cssSelector(".login-container h2")).getText(),
                String.format("Hello %s,",username));
        driver.findElement(By.className("logout-btn")).click();

        Thread.sleep(2000);
        driver.quit();
    }

    public static String getPassword(WebDriver driver) throws InterruptedException {
        driver.findElement(By.linkText("Forgot your password?")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
        String passwordText = driver.findElement(By.cssSelector("form p:nth-child(2)")).getText();

        driver.findElement(By.cssSelector(".go-to-login-btn")).click();
        return passwordText.split("'")[1].split("'")[0];
    }
}
