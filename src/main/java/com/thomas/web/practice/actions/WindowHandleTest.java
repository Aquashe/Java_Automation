package com.thomas.web.practice.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHandleTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Thread.sleep(3000);

        driver.findElement(By.cssSelector("a[class='blinkingText']")).click();

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();

        driver.switchTo().window(childId);
        Thread.sleep(2000);
        System.out.println(driver.findElement(By.cssSelector("p[class='im-para red']")).getText());

        String emailId = driver.findElement(By.cssSelector("p[class='im-para red']")).getText()
                .split("at")[1]
                .split("with")[0].trim();


        driver.switchTo().window(parentId);

        driver.findElement(By.id("username")).sendKeys(emailId);

        Thread.sleep(3000);
        driver.quit();
    }
}
