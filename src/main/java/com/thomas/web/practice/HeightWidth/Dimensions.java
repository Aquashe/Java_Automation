package com.thomas.web.practice.HeightWidth;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class Dimensions {
    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        // inputField WebElement
        WebElement inputEditBox = driver.findElement(By.cssSelector("input[name='name']"));

        // Swtch to new Tab
        driver.switchTo().newWindow(WindowType.TAB);

        //Get the control of new Tab
        ArrayList<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        // Hit with new URL
        driver.get("https://rahulshettyacademy.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,3500)");
        Thread.sleep(1000);
        String title = driver.findElement(By.xpath(
                "(//div[text()='Featured Courses'])/following::h3[1]")).getText();

        driver.switchTo().window(windows.get(0));
        Thread.sleep(1000);
        inputEditBox.sendKeys(title);

    // Height and Width
        System.out.println("Height "+inputEditBox.getRect().height);
        System.out.println("Width : "+inputEditBox.getRect().width);


        Thread.sleep(3000);
        driver.quit();

    }
}
