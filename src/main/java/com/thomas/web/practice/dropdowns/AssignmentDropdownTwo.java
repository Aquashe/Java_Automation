package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.List;

public class AssignmentDropdownTwo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        String country = "United Kingdom (UK)";
        driver.findElement(By.cssSelector("input#autocomplete")).click();
        driver.findElement(By.cssSelector("input#autocomplete")).sendKeys("uni");

        List<WebElement> autoSuggestions = driver.findElements(By.cssSelector(
                "li div[class='ui-menu-item-wrapper']"));
        autoSuggestions.stream()
                .filter(wb -> wb.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresent(WebElement::click);
        
        Thread.sleep(3000);
        driver.quit();
    }
}
