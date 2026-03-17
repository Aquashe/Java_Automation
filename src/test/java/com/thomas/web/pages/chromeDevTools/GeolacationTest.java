package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GeolacationTest extends CDP_BaseTest {

    @Test
    public void throughCdpCommandDeviceCoordinates() throws InterruptedException {
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude",40);
        coordinates.put("longitude",3);
        coordinates.put("accuracy",1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride",
                coordinates);

        launchApplication("http://google.com");

        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        Thread.sleep(20000);

        driver.findElement(By.cssSelector("#_Ofu2aZObLvvvseMPm42poAk_37")).click();
        Thread.sleep(2000);

        System.out.println(
                driver.findElement(By.className(" default-ltr-iqcdef-cache-8x0o3t eb5pmcc0")).getText());
    }
}
