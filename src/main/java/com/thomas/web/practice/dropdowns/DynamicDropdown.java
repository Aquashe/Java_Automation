package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DynamicDropdown {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        //From
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value = 'BLR']")).click();
        Thread.sleep(2000);

        //To
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='MAA']")).click();

        //Depart Date
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();
        Thread.sleep(3000);
        driver.quit();

        //Return Date
        driver.findElements(By.xpath("//*[@id=\"Div1\"]/button")).getFirst().click();
        driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).click();
    }
}
