package com.thomas.web.step_definitions;

import com.thomas.utils.WaitHelper;
import com.thomas.web.driver.DriverManager;
import com.thomas.web.pages.ecommerce.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PurchaseStepDefinitions {

    LoginPage loginPage;
    ProductCatologuePage productCatologuePage;
    MyCartPage myCartPage;
    PlaceOrderPage placeOrderPage;
    SuccesPage succesPage;
    WebDriver driver = DriverManager.getDriver();
     WaitHelper waitHelper = new WaitHelper(driver, 10);;
    @Given("Logged in with username {string} and password {string}")
    public void logged_in_with_username_and_password(String username, String password) {
        loginPage = new LoginPage(driver);
        productCatologuePage = loginPage.performLoginPage(
                username, password, true);
    }
    @When("i add product {string} to Cart")
    public void i_add_product_to_cart(String productName) {
        waitHelper.waitForElementTobeVisible(productCatologuePage.buttonCart);

        productCatologuePage.addProductToCart(productName);
        myCartPage = productCatologuePage.clickButtonCart();
    }
    @When("Checkout {string} and submit the order")
    public void checkout_and_submit_the_order(String productName) {
         myCartPage.checkProductedInsideCartOrNot(productName, true);
        placeOrderPage = myCartPage.performMyCartPageE2e(true);
        succesPage = placeOrderPage.performE2ePlaceOrderPage(
                "Brazil", true);
    }
    @Then("{string} message is displayed on SuccessPage.")
    public void message_is_displayed_on_success_page(String successMessage) {
        succesPage.getSuccessTitle();
        Assert.assertEquals(succesPage.getSuccessTitle().toLowerCase().trim(),
                successMessage.toLowerCase().trim());
    }
    @Then("{string} message is displayed")
    public void message_is_displayed(String errorMessage) {
        Assert.assertEquals(loginPage.getIncorrectEmailOrPasswordErrorText(),
                errorMessage);
    }
}
