package com.thomas.web.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/ecommerce",
        glue = {"com.thomas.web.step_definitions",
                "com.thomas.web.hooks"},
        tags = "@Regression",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/jsonReports/cucumber-report.json"
        }
)
public class EcommerceRunner extends AbstractTestNGCucumberTests {

}
