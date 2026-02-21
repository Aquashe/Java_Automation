package com.thomas.web.pages.ecommerce;

import com.thomas.web.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class E2eEcommerceTest extends BaseTest {

    @DataProvider(name = "loginAndProductsData")
    public Object[][] getLoginAndProductsData() {
        List<HashMap<String, Object>> hashMapDataLists =
                getTestData("//src//test//resources//testData//ecommerce//loginProducts.json");
        Object[][] data = new Object[hashMapDataLists.size()][3];
        for(int i =0; i<hashMapDataLists.size(); i++){
            HashMap<String, Object> map = hashMapDataLists.get(i);
            data[i][0] = (String) map.get("email");
            data[i][1] = (String) map.get("password");
            data[i][2] = (List<String>) map.get("products");
        }
        return data;
    }

    @Test(dataProvider = "loginAndProductsData")
    public void testCheckoutFlowWithMultipleProducts(
            String username, String password, List<String> productNames) {

        launchApplication("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        ProductCatologuePage productCatologuePage = loginPage.performLoginPage(
                username, password, true);

        watForPageElement(productCatologuePage.buttonCart);
        productNames.forEach(productCatologuePage::addProductToCart);
        MyCartPage myCartPage = productCatologuePage.clickButtonCart();

        productNames.forEach(
                product -> myCartPage.checkProductedInsideCartOrNot(product, true));
        PlaceOrderPage placeOrderPage = myCartPage.performMyCartPageE2e(true);

        SuccesPage succesPage = placeOrderPage.performE2ePlaceOrderPage(
                "Brazil", true);
        succesPage.getSuccessTitle();
        Assert.assertEquals(succesPage.getSuccessTitle().toLowerCase().trim(),
                "Thankyou for the order.".toLowerCase().trim());
    }

    @Test(dependsOnMethods = "testCheckoutFlowWithMultipleProducts", dataProvider = "loginAndProductsData")
    public void checkProductInOrders(String username, String password, List<String> productNames) {
        launchApplication("https://rahulshettyacademy.com/client/#/auth/login");
        LoginPage loginPage = new LoginPage(driver);
        ProductCatologuePage productCatologuePage = loginPage.performLoginPage(
                username, password, true);

        watForPageElement(productCatologuePage.buttonCart);
        OrderPage orderPage = productCatologuePage.clickButtonOrders();
        productNames.forEach(productName -> {
            Assert.assertTrue(orderPage.checkProductComeInOrders(productName),
                    String.format("Product : %s is not found in Orders", productName));
        });

    }
}
