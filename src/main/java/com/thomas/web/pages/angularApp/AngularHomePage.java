package com.thomas.web.pages.angularApp;

import com.thomas.utils.WaitHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AngularHomePage {
    WebDriver angularHomeDriver;
    WaitHelper waitHelper;

    public AngularHomePage(WebDriver driver){
        this.angularHomeDriver = driver;
        waitHelper = new WaitHelper(angularHomeDriver, 10);
        PageFactory.initElements(angularHomeDriver, this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(xpath = "//a[normalize-space()='RahulShettyAcademy']")
    private WebElement buttonRahulShettyAcademy;

    @FindBy(className = "navbar-toggler")
    private WebElement buttonNavbar;

    @FindBy(xpath = "//a[contains(text(), 'Products')]")
    private WebElement buttonProducts;

    @FindBy(xpath = "//a[contains(text(), 'Library')]")
    private WebElement buttonLibrary;

    @FindBy(xpath = "//a[contains(text(), 'Cart')]")
    private WebElement buttonCart;
    // REGION : END

    // REGION : PAGE METHODS
    public void clickButtonNavbar(){
        waitHelper.waitForElementTobeVisible(buttonNavbar);
        buttonNavbar.sendKeys(Keys.ENTER);
    }
    public void clickButtonRahulShettyAcademy(){
        waitHelper.waitForElementTobeVisible(buttonRahulShettyAcademy);
        buttonRahulShettyAcademy.sendKeys(Keys.ENTER);
    }

    public void clickButtonProducts(){
        waitHelper.waitForElementTobeVisible(buttonProducts);
        buttonProducts.sendKeys(Keys.ENTER);
    }

    public void clickButtonLibrary(){
        waitHelper.waitForElementTobeVisible(buttonLibrary);
        buttonLibrary.sendKeys(Keys.ENTER);
    }

    public void clickButtonCart(){
        waitHelper.waitForElementTobeVisible(buttonCart);
        buttonCart.sendKeys(Keys.ENTER);
    }

    public void performHomePage(
            boolean clickNavbar,
            boolean clickRahulShettyAcademy,
            boolean clickProducts,
            boolean clickLibrary,
            boolean clickCart){

        if(clickNavbar)
            this.clickButtonNavbar();
        if(clickRahulShettyAcademy)
            this.clickButtonRahulShettyAcademy();
        if(clickProducts)
            this.clickButtonProducts();
        if(clickLibrary)
            this.clickButtonLibrary();
        if(clickCart)
            this.clickButtonCart();
    }
    // REGION : END
}
