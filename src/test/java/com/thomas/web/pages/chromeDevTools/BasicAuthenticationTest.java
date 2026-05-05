package com.thomas.web.pages.chromeDevTools;

import com.thomas.web.base.CDP_BaseTest;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthenticationTest extends CDP_BaseTest {

    @Test
    public void handleNonSeleniumWindowLocator() throws InterruptedException {

        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
        launchApplication("https://httpbin.org/basic-auth/foo/bar");

        Thread.sleep(5000);
    }
}
