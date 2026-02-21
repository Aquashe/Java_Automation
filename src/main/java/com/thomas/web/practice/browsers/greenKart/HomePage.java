package com.thomas.web.practice.browsers.greenKart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage {


    public static void addProduct(WebDriver driver, String vegetableName) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='product']//h4[@class='product-name']"));
        for (WebElement product : products) {
            if (product.getText().contains(vegetableName)) {
                System.out.println("Adding Vegetable :" + vegetableName);
                Thread.sleep(1000);
                product.findElement(By.xpath("following::button[text()='ADD TO CART' or text()='✔ ADDED']")).click();
                break;
            }
        }
    }

    @Test
    public void addVegetableToCart() throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        Thread.sleep(1000);

        String[] vegetables = {"Cucumber", "Brocolli", "Beans"};
        for (int i = 0; i < vegetables.length; i++)
            addProduct(driver, vegetables[i]);

        driver.findElement(By.xpath("//a[text()='Flight Booking']/following::a[@class='cart-icon']")).click();
        driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();

        driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.className("promoBtn")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String appliedCodeText =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoInfo"))).getText();

        Assert.assertEquals(appliedCodeText, "Code applied ..!");

        Thread.sleep(3000);
        driver.quit();
    }
}
