package com.thomas.web.pages.angularApp;

import com.thomas.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class ProductListPage {
    WebDriver productListDriver;
    WaitHelper waitHelper;

    public ProductListPage(WebDriver driver){
        this.productListDriver = driver;
        waitHelper = new WaitHelper(productListDriver, 10);
        PageFactory.initElements(productListDriver,this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(xpath = "//h1[contains(text(), 'Product List')]")
    private WebElement titleProductList;

    @FindBy(xpath = "//button[contains(text(), 'Enable/Disable Buying')]")
    private WebElement buttonEnableOrDisableBuying;

    @FindBy(xpath = "//button[contains(text(), 'Enable/Disable Buying')]/following::ul/div")
    private List<WebElement> containerProductLists;

    // REGION : END

    // REGION : PAGE METHODS
    public void clickButtonEnableOrDisableBuying(){
        waitHelper.waitForElementTobeVisible(titleProductList);
        buttonEnableOrDisableBuying.sendKeys(Keys.ENTER);
    }

    public void chooseProduct(){
        waitHelper.waitForElementTobeVisible(titleProductList);
        Optional<WebElement> product = containerProductLists.stream()
                .filter(webElement -> {
                    WebElement buttonCheckAvailability = webElement.findElement(
                            By.xpath(".//button[contains(text(), 'Check Availability')]"));
                    return buttonCheckAvailability.isEnabled();
                }).findFirst();

        product.ifPresent(container ->
                container.findElement(By.xpath(".//a")).click());
    }
    // REGION : END
}
