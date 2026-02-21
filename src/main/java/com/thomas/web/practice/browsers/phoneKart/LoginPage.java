package com.thomas.web.practice.browsers.phoneKart;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class LoginPage {

    public static  WebDriver driver;

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("learning");
        driver.findElement(By.xpath("//input[@value='user']/following-sibling::span")).click();

        Thread.sleep(2000);
        driver.findElement(By.id("okayBtn")).click();

        Thread.sleep(200);
        Select options = new Select(
                driver.findElement(By.xpath("(//input[@value='user']/following-sibling::span)/following::select")));
        options.selectByVisibleText("Teacher");

        Thread.sleep(1500);
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();

    }

    @Test(priority = 2)
    public void addPhoneToCart() throws InterruptedException {
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        int phonesToAdd = driver.findElements(By.xpath("//button[@class='btn btn-info' and contains(text(),'Add')]")).size();
        ArrayList<String> phoneNames = new ArrayList<>();

        for(int i=1;i<=phonesToAdd;i++){
            WebElement phoneAdd = driver.findElement(By.xpath("(//button[@class='btn btn-info' and contains(text(),'Add')])["+i+"]"));
            String phoneName = phoneAdd.findElement(By.xpath("parent::div/preceding-sibling::div//a")).getText();
            if(!phoneNames.contains(phoneName)){
                phoneNames.add(phoneName);
                System.out.println("Add Phone : "+phoneName);
                wait.until(ExpectedConditions.elementToBeClickable(phoneAdd)).click();
            }
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='nav-link btn btn-primary']"))).click();
    }

    @Test(priority = 3)
    public void insideCartPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-hover']//th[1]"))).getText(),
                "Product");

        Thread.sleep(5000);
        driver.quit();
    }
}
