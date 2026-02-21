package com.thomas.web.practice.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class WindowHandlerTestTwo {

    public static WebDriver driver;
    public static ArrayList<String> windows;

    @Test(priority = 1)
    public void parentWindowFirst() throws InterruptedException {
        driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();

    }

    @Test(priority = 2)
    public void childWindow() {
        windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        System.out.println("Child : " + new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3"))).getText());
    }

    @Test(priority = 3)
    public void parentWindowSecond() throws InterruptedException {
        driver.switchTo().window(windows.getFirst());
        System.out.println("Parent : " + new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Click Here']/preceding-sibling::h3"))).getText());

        Thread.sleep(3000);
        driver.quit();
    }
}
