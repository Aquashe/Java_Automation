package com.thomas.web.practice.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class IntroThree {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.xpath("(//div/button[text()='Practice'])/following-sibling::button[1]"))
                .click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("(//div/button[text()='Practice'])/following-sibling::button[1]/parent::div/a"))
                        .click();
        Thread.sleep(2000);
        
        driver.quit();

    }
}
