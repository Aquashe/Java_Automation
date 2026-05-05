package com.thomas.web.pages.angularApp;

import com.thomas.utils.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddToCartPage {
    WebDriver addToCartDriver;
    WaitHelper waitHelper;

    public AddToCartPage(WebDriver driver){
        addToCartDriver = driver;
        waitHelper = new WaitHelper(addToCartDriver, 10);
        PageFactory.initElements(addToCartDriver, this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(xpath = "//button[contains(text(), 'Add to Cart')]")
    private WebElement buttonAddToCart;

    @FindBy(xpath = "//p[contains(text(), 'This Product is already added to Cart')]")
    private WebElement textProductAlreadyAddedToCart;
    // REGION : END

    // REGION : PAGE METHODS
    public void clickButtonAddToCart(){
        waitHelper.waitForElementTobeVisible(buttonAddToCart);
        buttonAddToCart.sendKeys(Keys.ENTER);
    }

    public void verifyProductedMessage(){
        waitHelper.waitForElementTobeVisible(textProductAlreadyAddedToCart);
        String actual = textProductAlreadyAddedToCart.getText();
        Assert.assertEquals(actual, "THIS PRODUCT IS ALREADY ADDED TO CART");
    }
    // REGION : END
}
