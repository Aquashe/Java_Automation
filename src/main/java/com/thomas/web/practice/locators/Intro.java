package com.thomas.web.practice.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Intro {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello");
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice");
        driver.manage().window().fullscreen();

        driver.findElement(By.id("inputUsername")).sendKeys("thomasukuttybenny2709@gmail.com");
        driver.findElement(By.name("inputPassword")).sendKeys("Thomas@2709");
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("123456");

        driver.findElement(By.cssSelector("input[type='text']:nth-child(2)")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(2)")).sendKeys("thomasukuttybenny2709@gmail.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("2324233233");

        driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();

        System.out.println(driver.findElement(By.cssSelector("form p:nth-child(2)")).getText());
        driver.findElement(By.cssSelector(".go-to-login-btn")).click();

        driver.findElement(By.cssSelector("#inputUsername")).sendKeys("rahul");
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.cssSelector("button[class='submit signInBtn']")).click();

        Thread.sleep(2000);

        driver.quit();
    }
}
