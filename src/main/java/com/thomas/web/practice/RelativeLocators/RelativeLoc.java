package com.thomas.web.practice.RelativeLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLoc {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        // Above
        WebElement inputEditBox = driver.findElement(By.cssSelector("input[name='name']"));
        System.out.println(
                driver.findElement(with(By.tagName("label")).straightAbove(inputEditBox)).getText());


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");


        // Below
        WebElement labelDob = driver.findElement(By.cssSelector("label[for='dateofBirth']"));
        driver.findElement(
                with(By.tagName("input")).straightBelow(labelDob)).click();
        Thread.sleep(2000);

        // Left
        js.executeScript("document.querySelector('input#exampleCheck1').scrollIntoView()");
        WebElement checkboxDescrption = driver.findElement(By.xpath(
                "//label[normalize-space()='Check me out if you Love IceCreams!']"));
        driver.findElement(with(By.tagName("input")).straightLeftOf(checkboxDescrption)).click();
        Thread.sleep(2000);

        //Right
        js.executeScript("document.querySelector('#inlineRadio1').scrollIntoView()");
        WebElement firstRadio = driver.findElement(By.cssSelector("#inlineRadio1"));
        System.out.println(driver.findElement(with(By.tagName("label")).straightRightOf(firstRadio)).getText());


        Thread.sleep(3000);
        driver.quit();
    }
}
