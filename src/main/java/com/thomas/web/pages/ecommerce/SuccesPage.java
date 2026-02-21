package com.thomas.web.pages.ecommerce;

import com.thomas.utils.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccesPage {
    private WebDriver successPageDriver;
    private WaitHelper waitHelper;

    public SuccesPage(WebDriver driver) {
        this.successPageDriver = driver;
        this.waitHelper = new WaitHelper(successPageDriver, 10);
        PageFactory.initElements(successPageDriver, this);
    }

    //REGION : PAGE LOCATORS
    @FindBy(css = ".hero-primary")
    private WebElement titleSuccess;
    // END REGION

    // REGION : PAGE METHODS
    public String getSuccessTitle() {
        waitHelper.waitForElementTobeVisible(titleSuccess);
        return titleSuccess.getText();
    }
    // END REGION
}
