package com.thomas.web.practice.streams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SeleniumStreams {
    WebDriver driver;
    Set<String> windows;
    WebDriverWait wait;
    List<WebElement> vegetablesList;
    ArrayList<String> vegiesFromWeb;
    ArrayList<String> vegiesSorted;

    @Parameters({"URL"})
    @Test(priority = 1)
    public void clickTopDeals(String url) {
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        // 1.Click on Top Deals
        System.out.println("1. Click on Top Deals");
        driver.findElement(By.xpath("//a[text()='Top Deals']")).click();
    }

    @BeforeTest()
    public void printHello(){
        System.out.println("Hello");
    }
    @Test(priority = 2)
    public void switchToNewWindow() {
        // 2. Give ctrl to new window
        System.out.println("2. Give Control to new Window.");
        windows = driver.getWindowHandles();
        String childWindow = windows.stream().reduce((a, b) -> b).orElse("empty");
        driver.switchTo().window(childWindow);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.cssSelector("label[for='deliveryDate']"))));
    }

    @Test(priority = 3)
    public void checkVegetablesAreOrderedOrNot() throws InterruptedException {
        driver.findElement(By.cssSelector("span[class='sort-icon sort-descending']")).click();
        Thread.sleep(2000);

        storeVegetableIntoList();
        createOrderedVegiesList();
        Assert.assertEquals(vegiesFromWeb, vegiesSorted, "Lists are not same");

        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void getVegetablePrice() throws InterruptedException {
        String vegetableName = "Wheat";
        WebElement next = driver.findElement(By.cssSelector("a[aria-label='Next']"));

        while ((priceVegetable(vegetableName) == null) && (next.getAttribute(
                "aria-disabled").matches("false"))) {
            next.click();
            Thread.sleep(1000);
        }
        if ((priceVegetable(vegetableName) == null)) {
            System.out.println("No Vegetable Found");
        } else {
            String price = priceVegetable(vegetableName)
                    .findElement(By.xpath("following-sibling::td"))
                    .getText();
            System.out.println("The price of " + vegetableName + " : " + price);
        }
    }

    @Test(priority = 5)
    public void checkFilter() {
        String filter = "Rice";
        driver.findElement(By.id("search-field")).sendKeys(filter);

        storeVegetableIntoList();
        int size = vegetablesList.stream()
                .map(WebElement::getText)
                .filter(s -> s.contains(filter))
                .collect(Collectors.toCollection(ArrayList::new)).size();
        Assert.assertEquals(size, 1);

        driver.quit();
    }

    public WebElement priceVegetable(String vegName) {
        storeVegetableIntoList();
        return vegetablesList.stream()
                .filter(vegetable -> vegetable.getText().equalsIgnoreCase(vegName))
                .findAny()
                .orElse(null);
    }

    public void storeVegetableIntoList() {
        vegetablesList = driver.findElements(By.cssSelector(
                "table[class='table table-bordered'] tbody tr td:nth-child(1)"));

        vegiesFromWeb = vegetablesList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void createOrderedVegiesList() {
        vegiesSorted = vegiesFromWeb.stream()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
