package com.thomas.web.pages.ecommerce;

import com.thomas.web.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductCatologueTest extends BaseTest {

    List<String> productNames = List.of("ZARA COAT 3");
    @Test(groups = {"ErrorHandling"})
    public void checkWrongProductNotAdded(){
        launchApplication("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        ProductCatologuePage productCatologuePage = loginPage.performLoginPage(
                "gfdsaewq@gmail.com","Plate@123456", true);

        watForPageElement(productCatologuePage.buttonCart);
        productNames.forEach(productCatologuePage::addProductToCart);
        MyCartPage myCartPage = productCatologuePage.clickButtonCart();
        myCartPage.checkProductedInsideCartOrNot("iphone 15 pro", false);

    }
}
