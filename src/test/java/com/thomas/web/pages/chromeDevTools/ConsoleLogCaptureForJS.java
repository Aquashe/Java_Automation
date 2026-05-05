package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.BaseTest;
import com.thomas.web.pages.angularApp.AddToCartPage;
import com.thomas.web.pages.angularApp.AngularHomePage;
import com.thomas.web.pages.angularApp.CartPage;
import com.thomas.web.pages.angularApp.ProductListPage;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import java.util.List;

public class ConsoleLogCaptureForJS extends BaseTest {

    /**
     * For such scenarios u can use the TestNG Listener class for that as like used for failed scenario Screenshots
     */
    @Test
    public void extractConsoleLogForJS_Failures() {

        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(false, false,
                false, false,
                false, true,
                false);

        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.clickButtonEnableOrDisableBuying();
        productListPage.chooseProduct();

        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage.clickButtonAddToCart();
        addToCartPage.verifyProductedMessage();

        angularHomePage.performHomePage(false, false,
                false, false,
                true, false,
                false);

        CartPage cartPage = new CartPage(driver);
        cartPage.enterQuantity("2");

        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> logEntryList = logEntries.getAll();
        System.out.println("Logs : \n");
        for (LogEntry logEntry : logEntryList)
            System.out.println(logEntry.getMessage());

    }
}
