package com.thomas.web.practice.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class RightClickTest {
    public static void main(String [] args) throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@class='nav-line-2 '] ")))
                .contextClick()
                .build()
                .perform();


        Thread.sleep(3000);
        driver.quit();
    }
}
