package com.thomas.web.pages.ecommerce;

import com.thomas.utils.WaitHelper;
import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver loginDriver;
    WaitHelper waitHelper;

    public  LoginPage(WebDriver driver){
        this.loginDriver = driver;
        waitHelper = new WaitHelper(loginDriver, 10);
        PageFactory.initElements(loginDriver, this);
    }

// REGION : PAGE LOCATORS
    @FindBy(id = "userEmail")
    WebElement textFieldEmail;

    @FindBy(id = "userPassword")
    WebElement textFieldPassword;

    @FindBy(id = "login")
    WebElement buttonLogin;

    @FindBy(css = "div[class*='flyInOut']")
    WebElement textIncorrectEmailOrPassword;
//    .ng-tns-c4-13.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

// END REGION

// REGION : PAGE METHODS
    public ProductCatologuePage clickLogin(){
        buttonLogin.sendKeys(Keys.chord(Keys.ENTER));
        return new ProductCatologuePage(loginDriver);
    }

    public String getIncorrectEmailOrPasswordErrorText(){
        waitHelper.waitForElementTobeVisible(textIncorrectEmailOrPassword);
        return textIncorrectEmailOrPassword.getText().trim();
    }

    public ProductCatologuePage performLoginPage(String email, String password, boolean clickLogin){
        if(!StringUtils.isNullOrEmpty(email))
            textFieldEmail.sendKeys(email);
        if(!StringUtils.isNullOrEmpty(password))
            textFieldPassword.sendKeys(password);
        return clickLogin ? this.clickLogin() : null;
    }
// END REGION
}
