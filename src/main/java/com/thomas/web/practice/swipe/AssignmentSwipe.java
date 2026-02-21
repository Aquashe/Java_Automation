package com.thomas.web.practice.swipe;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.List;

public class AssignmentSwipe {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(2000);

        int tableRows = driver.findElements(By.cssSelector("table.table-display tr")).size();
        int tableColumns = driver.findElements(By.cssSelector("table.table-display tr th")).size();

        System.out.println("Row Size :"+tableRows);
        System.out.println("Column Size :"+tableColumns);

        System.out.println();
        List<WebElement> cources = driver.findElements(By.cssSelector(
                "table.table-display tr td:nth-child(2) "));
        for(WebElement course : cources)
            System.out.println("Course :\t"+course.getText());

        Thread.sleep(3000);
        driver.quit();
    }
}
