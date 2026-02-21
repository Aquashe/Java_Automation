package com.thomas.web.practice.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class JavaAlerts {
    public static void main(String [] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();

        String text = "Rahul";
        driver.findElement(By.id("name")).sendKeys(text);
        driver.findElement(By.id("alertbtn")).click();

        // 1. First Alert
        Thread.sleep(2000);
        Alert alertOne = driver.switchTo().alert();
        System.out.println(alertOne.getText());
        alertOne.accept();

        // 2. Second Alert
        driver.findElement(By.id("name")).sendKeys(text);
        driver.findElement(By.id("confirmbtn")).click();

        Thread.sleep(2000);
        Alert alertTwo = driver.switchTo().alert();
        System.out.println(alertTwo.getText());
        alertTwo.dismiss();

        Thread.sleep(3000);
        driver.quit();

    }
}
