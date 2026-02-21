package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class LatestIncrementalStaticDropdown {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000);

        for (int i = 0; i < 3; i++)
            driver.findElement(By.id("hrefIncAdt")).click();

        driver.findElement(By.id("btnclosepaxoption")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
