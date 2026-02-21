package com.thomas.web.pages.ecommerce;


import com.thomas.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyCartPage {
    private WebDriver myCartDriver;
    private WaitHelper waitHelper;
    Actions actions;

    public MyCartPage(WebDriver driver){
        this.myCartDriver = driver;
        this.waitHelper = new WaitHelper(driver, 10);
        PageFactory.initElements(myCartDriver, this);
        this.actions = new Actions(myCartDriver);
    }

    // REGION : PAGE OBJECTS
    @FindBy(xpath = "//h1[text()='My Cart']")
    private WebElement titleMyCart;

    @FindBy(xpath = "//div[@class='infoWrap']")
    private List<WebElement> cartProductContainersList;

    @FindBy(xpath = "//button[text()='Checkout']")
    private WebElement buttonCheckOut;
    // END REGION

    // REGION : PAGE METHODS
    private List<String > addedProductList(){
        waitHelper.waitForElementTobeVisible(titleMyCart);
        ArrayList<String> cartProductNames = cartProductContainersList.stream()
                .map(webElement -> webElement.findElement(By.xpath(".//h3")))
                .map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));

        return cartProductNames.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public void checkProductedInsideCartOrNot(String productName, boolean trueCheck){
        List<String > normalizedCartNames  = addedProductList();
        if(trueCheck)
            Assert.assertTrue(normalizedCartNames.contains(productName.trim().toLowerCase()),
                String.format("%s not added inside cart", productName));
        else
            Assert.assertFalse(normalizedCartNames.contains(productName.trim().toLowerCase()),
                    String.format("Product %s can't be inside cart", productName));
    }

    public PlaceOrderPage clickButtonCheckOut(){
        waitHelper.waitForElementTobeClickable(buttonCheckOut);
        actions.scrollToElement(buttonCheckOut).build().perform();
        buttonCheckOut.click();
        return new PlaceOrderPage(myCartDriver);
    }

    public PlaceOrderPage performMyCartPageE2e(boolean clickButtonCheckOut){
        return clickButtonCheckOut ? this.clickButtonCheckOut() : null;
    }
    // END REGION

}
