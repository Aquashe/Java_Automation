package com.thomas.web.practice.screenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

public class PartialScreenshot {
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
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,3500)");
        Thread.sleep(1000);
        String title = driver.findElement(By.xpath(
                "(//div[text()='Featured Courses'])/following::h3[1]")).getText();

        driver.switchTo().window(windows.get(0));
        Thread.sleep(1000);
        inputEditBox.sendKeys(title);

        File file = inputEditBox.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(
                "C:\\Users\\Wonder\\OneDrive\\Desktop\\SeleniumJava\\src\\test\\screenshots\\partial_screenshot.png"));




        Thread.sleep(3000);
        driver.quit();

    }
}
