package com.thomas.web.pages.angularApp;

import com.thomas.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver cartDriver;
    WaitHelper waitHelper;

    public CartPage(WebDriver driver){
        this.cartDriver =driver;
        waitHelper = new WaitHelper(cartDriver, 10);
        PageFactory.initElements(cartDriver, this);
    }


    // REGION : PAGE LOCATORS
    @FindBy(id = "exampleInputEmail1")
    private WebElement textFieldQuantity;

    // REGION : END

    // REGION : PAGE METHODS
    public void enterQuantity(String quantity){
        waitHelper.waitForElementTobeVisible(textFieldQuantity);
        textFieldQuantity.clear();
        textFieldQuantity.sendKeys(quantity);
    }
    // REGION : END
}
