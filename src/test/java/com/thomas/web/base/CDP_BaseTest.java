package com.thomas.web.base;

import com.thomas.utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class CDP_BaseTest {
    protected ChromeDriver driver;
    protected ConfigReader configReader;
    protected DevTools devTools;

    private ChromeDriver intializeDriver(){
        configReader = new ConfigReader(
                System.getProperty("user.dir")+"//src//main//resources//GlobalData.properties");
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : configReader.getValue("browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private DevTools intilizeDevTools(){
        devTools = getDriver().getDevTools();
        devTools.createSession();
        return devTools;
    }

    public void launchApplication(String url){
        driver.get(url);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = this.intializeDriver();
        devTools = this.intilizeDevTools();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(driver!= null)
            driver.quit();
    }

    public ChromeDriver getDriver(){
        return driver;
    }

    public DevTools getDevTools(){
        return devTools;
    }
}
