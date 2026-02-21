package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.util.List;

public class AutoSuggestionDropdown {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Checkbox Selections
        driver.findElements(By.xpath("//div[@id='discount-checkbox']//input[@type='checkbox']")).forEach(webElement -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            webElement.click();
        });
        driver.findElement(By.cssSelector("input[id*='friendsandfamily']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='friendsandfamily']")).isSelected());


        //Radio Buttons
        for (WebElement wb : driver.findElements(By.xpath("//table[@class='tblTrip']//input[@type='radio']"))) {
            if (driver.findElement(By.xpath("//div[@id='Div1']")).getDomAttribute("style").contains("1"))
                break;
            wb.click();
        }


        //Dropdown Selections
        driver.findElement(By.id("autosuggest")).click();
        String country = "British Indian Ocean Territory";
        driver.findElement(By.id("autosuggest")).sendKeys("br");
        Thread.sleep(2000);
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        options.stream()
                .filter(webElement -> webElement.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresentOrElse(WebElement::click, () -> System.out.println("No match"));

        Thread.sleep(3000);
        driver.quit();
    }
}
