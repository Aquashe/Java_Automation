package com.thomas.web.practice.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;


public class FluentWait {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div[id='start'] button")).click();
        Wait<WebDriver> wait = new org.openqa.selenium.support.ui.FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(12))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);


        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                WebElement element = driver.findElement(By.cssSelector("div[id='finish'] h4"));
                if(element.isDisplayed())
                    return element;
                return null;
            }
        });

        System.out.println(driver.findElement(By.cssSelector("div[id='finish'] h4")).getText());

        Thread.sleep(2000);
        driver.quit();


    }
}
