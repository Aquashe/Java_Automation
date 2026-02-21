package com.thomas.web.base;

import com.thomas.reporting.ExecutionManager;
import com.thomas.utils.ConfigReader;
import com.thomas.utils.TestDataReader;
import com.thomas.utils.WaitHelper;
import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class BaseTest {
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected ConfigReader configReader;

    private WebDriver intializeDriver(){
        configReader = new ConfigReader(
                System.getProperty("user.dir")+"//src//main//resources//GlobalData.properties");
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : configReader.getValue("browser");

        if(browserName.equalsIgnoreCase("Chrome")){
            // These headless lines are optional . If you want to integrate these just add these 2 lines
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("headless");
            driver = new ChromeDriver(chromeOptions);
            // Since we are running with headless m
            // ight maximize issue will come . To avoid that these code wil help
            driver.manage().window().setSize(new Dimension(1440,900));
        }
        else if (browserName.equalsIgnoreCase("FireFox"))
            driver = new FirefoxDriver();
        else if (browserName.equalsIgnoreCase("Edge"))
            driver = new EdgeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void launchApplication(String url){
        driver.get(url);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = this.intializeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(driver!= null)
            driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void watForPageElement(WebElement webElement){
        waitHelper = new WaitHelper(driver, 10);
        waitHelper.waitForElementTobeVisible(webElement);
    }

    public List<HashMap<String, Object>> getTestData(String filePath){
        return new TestDataReader(filePath).getJsonDataToMap();
    }

    public String getScreenshot(String testCaseName, WebDriver driverScreenshot) {

        String fullClassName = this.getClass().getPackage().getName();
        String module = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String className = this.getClass().getSimpleName();

        String screenshotDir = ExecutionManager.getExecutionFolder()
                + "\\" + module + "\\" + className + "\\screenshots\\";

        File file = new File(screenshotDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = screenshotDir + testCaseName + ".png";
        TakesScreenshot ts = (TakesScreenshot) driverScreenshot;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new File(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Screenshot File : " + filePath + " didn't get created.", e);
        }

        return module + "/" + className + "/screenshots/" + testCaseName + ".png";
    }

}
