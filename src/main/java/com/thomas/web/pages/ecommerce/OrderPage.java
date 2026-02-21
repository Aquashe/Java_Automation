package com.thomas.web.pages.ecommerce;

import com.thomas.web.base.BasePage;
import com.thomas.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends BasePage {
    private WebDriver orderDriver;

    public OrderPage(WebDriver driver){
        this.orderDriver = driver;
        waitHelper = new WaitHelper(orderDriver, 10);
        actions = new Actions(orderDriver);
        PageFactory.initElements(orderDriver, this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(xpath = "//h1[normalize-space()='Your Orders']/following::table//tbody/tr")
    List<WebElement> ordersRow;

    // END REGION

    // REGION : PAGE METHODS
    public boolean checkProductComeInOrders(String productName){
        return ordersRow.stream()
                .map(row->row.findElement(By.xpath(".//td[2]")))
                .anyMatch(webElement -> {
                    System.out.println("Product Inside Orders :"+webElement.getText());
                    return webElement.getText().equalsIgnoreCase(productName);
                });
    }
    // END REGION


}
