package com.thomas.web.practice.browsers.qaclickAcademy;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Scope {

    WebDriver driver;
    WebElement footerSection;
    WebElement columnFooter;
    int columnFooterTagSize = 0;
    List<WebElement> columnLinkTags;
    Set<String> windows;

    @Test(priority = 1)
    public void linksTagCountWholePage() {
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // 1. Give me the count of Links count of whole page
        System.out.println("Size : " + driver.findElements(By.tagName("a")).size());
    }

    @Test(priority = 2)
    public void linksTagCountFooter() {
        //2. Get the links count inside the footer section
        footerSection = driver.findElement(By.id("gf-BIG"));
        System.out.println("Footer A size : " + footerSection.findElements(By.tagName("a")).size());
    }

    @Test(priority = 3)
    public void linksTagCountFooterColumn() {
        //3. Find the links inside the first column of footer section
        columnFooter = footerSection.findElement(
                By.xpath("//table//tbody//tr//td[1]/ul"));
        columnFooterTagSize = columnFooter.findElements(By.tagName("a")).size();
        System.out.println("Footer Column A size : " + columnFooterTagSize);
    }

    //    @Test(priority = 4)
    public void clickLinksTagInsideFooterColumn() throws InterruptedException {
        //4. Click on each links inside the column footer
        columnLinkTags = columnFooter.findElements(By.tagName("a"));
        Thread.sleep(2000);

        Actions actions = new Actions(driver);
        for (int i = 1; i < columnFooterTagSize; i++) {
            System.out.println("Click Link : " + columnLinkTags.get(i).getText());
            actions.moveToElement(columnLinkTags.get(i))
                    .keyDown(Keys.CONTROL)
                    .click()
                    .build()
                    .perform();
            Thread.sleep(2000);
        }

        Thread.sleep(3000);
        driver.quit();
    }

    @Test(priority = 4)
    public void clickAndPrintHedingsLinkTagInsideFooterColumn() throws InterruptedException {
        //4. Click on each links inside the column footer
        columnLinkTags = columnFooter.findElements(By.tagName("a"));
        String ctrlClick = Keys.chord(Keys.CONTROL, Keys.ENTER);
        Thread.sleep(2000);

        System.out.println();
        for (int i = 1; i < columnFooterTagSize; i++) {
            System.out.print("Click Link : " + columnLinkTags.get(i).getText());
            columnLinkTags.get(i).sendKeys(ctrlClick);
            Thread.sleep(1000);

            windows = driver.getWindowHandles();
            String newTab = windows.stream()
                    .reduce((a, b) -> b)
                    .orElse("Not found");

            System.out.println("\t\tTab Title : " + driver.switchTo().window(newTab).getTitle());
            driver.switchTo().window(windows.stream().findFirst().orElse(""));
            Thread.sleep(1000);
        }

        Thread.sleep(3000);
        driver.quit();
    }
}
