package com.thomas.web.pages.chromeDevTools;

import com.google.common.collect.ImmutableList;
import com.thomas.web.base.CDP_BaseTest;
import com.thomas.web.pages.angularApp.AddToCartPage;
import com.thomas.web.pages.angularApp.AngularHomePage;
import com.thomas.web.pages.angularApp.ProductListPage;
import org.openqa.selenium.devtools.v143.network.Network;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

public class BlockNetworking extends CDP_BaseTest {
    /**
     * Purpose is to block the images so that save time
     */
    @Test
    public void blockNetworkImages() throws InterruptedException {
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        devTools.send(Network.setBlockedURLs(Optional.empty(),
                Optional.of(List.of("*.jpg", "*.css"))));

        long startTime = System.currentTimeMillis();
        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(false, false,
                false, false,
                false, true ,
                false);

        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickButtonEnableOrDisableBuying();
        productListPage.chooseProduct();

        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage.clickButtonAddToCart();
        addToCartPage.verifyProductedMessage();
        long endTime = System.currentTimeMillis();

        System.out.println("Time Taken "+(endTime-startTime));
        Thread.sleep(5000);
    }

}
