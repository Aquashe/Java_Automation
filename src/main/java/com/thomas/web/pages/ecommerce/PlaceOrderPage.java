package com.thomas.web.pages.ecommerce;


import com.thomas.utils.WaitHelper;
import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage{
    private WebDriver placeOrderDriver;
    private WaitHelper waitHelper;
    private Actions actions;

    public PlaceOrderPage(WebDriver driver){
        this.placeOrderDriver = driver;
        this.waitHelper = new WaitHelper(placeOrderDriver, 10);
        this.actions =new Actions(placeOrderDriver);
        PageFactory.initElements(placeOrderDriver,this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement textFieldCoutry;

    @FindBy(xpath = "//a[text()='Place Order ']")
    WebElement buttonPlaceOrder;
    // END REGION

    // REGION : PAGE METHODS
    public void chooseCountry(String country){
        textFieldCoutry.click();
        int len = country.length();
        actions.sendKeys(textFieldCoutry, country.substring(0, len - (len-2) ))
                .build().perform();

        textFieldCoutry.findElements(By.xpath(
                        "following-sibling::section/button")).stream()
                .filter(contryElement->contryElement.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public SuccesPage clickButtonPlaceOrder(){
        waitHelper.waitForElementTobeClickable(buttonPlaceOrder);
        buttonPlaceOrder.click();
        return new SuccesPage(placeOrderDriver);
    }

    public SuccesPage performE2ePlaceOrderPage(String country, boolean clickPlaceOrder){
        if(!StringUtils.isNullOrEmpty(country))
            this.chooseCountry(country);
        return clickPlaceOrder ? this.clickButtonPlaceOrder() : null;
    }
    // END REGION
}
