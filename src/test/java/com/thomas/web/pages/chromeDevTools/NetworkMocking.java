package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import com.thomas.web.pages.angularApp.AngularHomePage;
import org.openqa.selenium.devtools.v142.fetch.Fetch;
import org.openqa.selenium.devtools.v142.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v142.network.model.ErrorReason;
import org.openqa.selenium.devtools.v142.network.model.Request;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NetworkMocking extends CDP_BaseTest {

    @Test(enabled = false)
    public void createMockResponsePass() throws InterruptedException {
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            Request request =  requestPaused.getRequest();
            String mockUrl = request.getUrl();
            if(mockUrl.contains("=shetty")){
                mockUrl = request.getUrl().replace("=shetty", "=BadGuy");
                System.out.println("Mock Url : "+mockUrl);
            }
            devTools.send(Fetch.continueRequest(requestPaused.getRequestId(),
                    Optional.of(mockUrl), Optional.of(request.getMethod()),
                    Optional.empty(), Optional.empty(),
                    Optional.empty()));
        });
        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(false, false,
                false, true, false);

        Thread.sleep(30000);
    }

    @Test
    public void createNetworkMockingFailed() throws InterruptedException {
        devTools.createSession();

        List<RequestPattern> requestPatternList= new ArrayList<>();
        requestPatternList.add(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(),
                Optional.empty()));

        devTools.send(Fetch.enable(Optional.of(requestPatternList), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });

        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");

        AngularHomePage angularHomePage = new AngularHomePage(driver);
        angularHomePage.performHomePage(false, false,
                false, true, false);

        Thread.sleep(30000);
    }
}
