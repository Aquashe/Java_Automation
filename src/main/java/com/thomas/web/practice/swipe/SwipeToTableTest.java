package com.thomas.web.practice.swipe;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SwipeToTableTest {
    public static void main(String [] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(2000);

        js.executeScript("document.querySelector(\"div[class='tableFixHead']\").scrollTop = 5000");

        List<WebElement> tableAmountValues = driver.findElements(By.cssSelector(
                "div[class='tableFixHead'] tr td:nth-child(4)"));
        int sum =0;
        for(WebElement amount :tableAmountValues)
            sum += Integer.parseInt(amount.getText());

        WebElement totalAmountElement = driver.findElement(By.cssSelector("div.totalAmount"));
        int totalAmountElementSize = totalAmountElement.getText().length();
        String expected = totalAmountElement.getText().substring(totalAmountElementSize-3, totalAmountElementSize);

        Assert.assertEquals(String.valueOf(sum), expected);

        Thread.sleep(3000);
        driver.quit();
    }
}
