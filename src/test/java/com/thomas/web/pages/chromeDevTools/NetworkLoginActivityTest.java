package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import org.openqa.selenium.devtools.v142.network.model.Request;
import org.openqa.selenium.devtools.v142.network.model.Response;
import org.openqa.selenium.devtools.v142.network.Network;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkLoginActivityTest extends CDP_BaseTest {

    @Test
    public void checkNetworkLogs(){
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(),requestWillBeSent -> {
            Request request = requestWillBeSent.getRequest();
            System.out.println("Request Detals : "+request.getUrl());
        });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            System.out.println("Response Details : "+response.getUrl());
            System.out.println("Response Details : "+response.getStatus());
        });

        launchApplication("https://rahulshettyacademy.com/angularAppdemo/");
    }
}
