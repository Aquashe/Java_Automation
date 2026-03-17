package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import com.thomas.web.pages.angularApp.AngularHomePage;
import org.openqa.selenium.devtools.v142.emulation.Emulation;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MobileEmulatorTest extends CDP_BaseTest {

    @Test(enabled = false)
    public void throughSeleniumCdpCommand() {
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true,
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty()));

        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(true, false,
                false, true, false);
    }

    @Test()
    public void throughCdpCommandDeviceEmulator() {
        Map<String, Object> deviceMetrices = new HashMap();
        deviceMetrices.put("width", 500);
        deviceMetrices.put("height", 1000);
        deviceMetrices.put("deviceScaleFactor", 50);
        deviceMetrices.put("mobile", true);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",
                deviceMetrices);

        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(true, false,
                false, true, false);
    }


}
