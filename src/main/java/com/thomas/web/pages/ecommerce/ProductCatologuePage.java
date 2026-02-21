package com.thomas.web.pages.ecommerce;

import com.thomas.utils.WaitHelper;
import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class ProductCatologuePage {

    private WebDriver productCatologueDriver;
    private WaitHelper waitHelper;
    private Actions actions;

    public ProductCatologuePage(WebDriver driver) {
        this.productCatologueDriver = driver;
        this.waitHelper = new WaitHelper(productCatologueDriver, 10);
        PageFactory.initElements(productCatologueDriver, this);
        actions = new Actions(productCatologueDriver);
    }

    // REGION : LOCATORS
    @FindBy(xpath = "//button[contains(text(),'Cart' ) and @class = 'btn btn-custom']")
    public WebElement buttonCart;

    @FindBy(xpath = "//button[contains( translate( normalize-space(.) ,  'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz' ), 'orders'  )]")
    private WebElement buttonOrders;

    @FindBy(css = ".col-lg-4.col-md-6.col-sm-10")
    private List<WebElement> products;

    @FindBy(css = "#toast-container")
    private WebElement addedPopup;

    @FindBy(css = ".ng-animating")
    private WebElement loading;

    // END : LOCATORS


    // REGION : PAGE METHODS
    public MyCartPage clickButtonCart(){
        waitHelper.waitForElementTobeClickable(buttonCart);
        actions.scrollToElement(buttonCart).build().perform();
        buttonCart.click();
        return  new MyCartPage(productCatologueDriver);
    }

    public OrderPage clickButtonOrders(){
        waitHelper.waitForElementTobeClickable(buttonOrders);
        actions.scrollToElement(buttonOrders).build().perform();
        buttonOrders.click();
        return  new OrderPage(productCatologueDriver);
    }

    public void addProductToCart(String productName){
        WebElement productElement = products.stream()
                .map(webElement -> webElement.findElement(By.cssSelector(" h5")))
                .filter(product -> product.getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

        if (productElement != null) {
            actions.scrollToElement(productElement).build().perform();
            WebElement buttonProductAddToCart = productElement.findElement(By.xpath(
                    "following-sibling::button[last()]"));

            actions.scrollToElement(buttonProductAddToCart).build().perform();
            System.out.println("Adding Product : "+productName);
            buttonProductAddToCart.click();
        }
        waitHelper.waitForElementTobeVisible(addedPopup);
        waitHelper.waitForElementTobeInvisible(loading);

    }

    public MyCartPage performProductCatologuePage(boolean clickButtonCart, String productName){
        if(!StringUtils.isNullOrEmpty(productName))
            this.addProductToCart(productName);
        return clickButtonCart ? this.clickButtonCart() : null;
    }
    // END : PAGE METHODS

}
