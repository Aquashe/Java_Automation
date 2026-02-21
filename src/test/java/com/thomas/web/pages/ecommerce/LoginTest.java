package com.thomas.web.pages.ecommerce;

import com.thomas.listeners.RetryListener;
import com.thomas.web.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"Login","ErrorHandling"}, retryAnalyzer = RetryListener.class)
    public void loginWithIncorrectEmailOrPassword(){
        launchApplication("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.performLoginPage("gfdsqwefwfaewq@gmail.com","Plate@123456", true);
        Assert.assertEquals(loginPage.getIncorrectEmailOrPasswordErrorText(),
                "Incorrect email ora password.");
    }
}
