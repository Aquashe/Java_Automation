package com.thomas.web.practice.brokenLinks;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrokenUrl {
    public static void main(String [] args) throws InterruptedException, IOException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, 2000)");


        WebElement footerDriver = driver.findElement(By.xpath("//div[@id='gf-BIG']"));
        List<WebElement> linkTags = footerDriver.findElements(By.xpath(
                "//tbody//tr/td//a[not(contains(@href, '#'))]"));

        ArrayList<String> linksList = new ArrayList<>();
        linkTags.forEach(link-> linksList.add(link.getAttribute("href")));

        SoftAssert softAssert = new SoftAssert();
        for(String link : linksList){
            HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            System.out.println(link + " : " +conn.getResponseCode());
            softAssert.assertTrue(conn.getResponseCode()<400,
                    "The Link : "+link+" with code : "+conn.getResponseCode());
        }
        softAssert.assertAll();

        Thread.sleep(3000);
        driver.quit();
    }
}
