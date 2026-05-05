package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import com.thomas.web.pages.angularApp.AngularHomePage;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.ConnectionType;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkSpeedTest extends CDP_BaseTest {

    /**
     * Emulate Network speed using selenium
     */
    @Test
    public void controlNetworkSpeed() throws InterruptedException {
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty()));

        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 10000,
                Optional.of(ConnectionType.WIFI), Optional.empty(), Optional.empty(),
                Optional.empty()));

        /**
         * If in case any failed happened to events such as loading failed . U can use the Network event named Network.loadFailed
         */
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            System.out.println("Error Text : "+loadingFailed.getErrorText());
            System.out.println("TimeStamp :"+loadingFailed.getTimestamp());
        });

        long startTime = System.currentTimeMillis();
        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(false, false,
                false, false,
                false, false,
                true);
        long endTime = System.currentTimeMillis();

        System.out.println("Time Taken " + (endTime - startTime));
        Thread.sleep(5000);
    }
}
