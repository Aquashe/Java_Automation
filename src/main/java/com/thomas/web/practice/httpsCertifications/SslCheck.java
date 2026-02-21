package com.thomas.web.practice.httpsCertifications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class SslCheck {
    public static void main(String[] args) throws InterruptedException {
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);

        WebDriver driver = new EdgeDriver(options);
        driver.get("https://expired.badssl.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        System.out.println(driver.getTitle());
        Thread.sleep(3000);
        driver.quit();
    }
}
