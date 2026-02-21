package com.thomas.web.hooks;

import com.thomas.web.base.BaseTest;
import com.thomas.web.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class EcommerceHooks extends BaseTest{

    @Before
    public void beforePurchaseOrder(){
        setUp();
        WebDriver baseDriver = getDriver();
        DriverManager.setDriver(baseDriver);
        DriverManager.getDriver().get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @After
    public void afterPurchaseOrder(){
        tearDown();
    }
}
