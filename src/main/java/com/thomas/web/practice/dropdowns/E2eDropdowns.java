package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class E2eDropdowns {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();

        // 1. AutoSuggestion Selections(Countries)
        driver.findElement(By.id("autosuggest")).click();
        String country = "British Indian Ocean Territory";
        driver.findElement(By.id("autosuggest")).sendKeys("br");
        Thread.sleep(2000);
        List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        options.stream()
                .filter(webElement -> webElement.getText().equalsIgnoreCase(country))
                .findFirst()
                .ifPresentOrElse(WebElement::click, ()-> System.out.println("No match"));


        // 2. Radio Buttons
        for(WebElement wb : driver.findElements(By.xpath("//table[@class='tblTrip']//input[@type='radio']"))){
            if(driver.findElement(By.xpath("//div[@id='Div1']")).getDomAttribute("style").contains("1"))
                break;
            wb.click();
        }

        // 3. From
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value = 'BLR']")).click();
        Thread.sleep(2000);

        // 4. To
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR']//a[@value='MAA']")).click();

        // 5. Depart Date
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();

        // 6. Return Date
        driver.findElements(By.xpath("//span[@id='view_fulldate_id_2']/preceding-sibling::button[1]")).getFirst().click();
        driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-active']")).click();
        Thread.sleep(2000);

        // 7. Incremental Dropdwons(Adults)
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000);

        for (int i = 0; i < 3; i++)
            driver.findElement(By.id("hrefIncAdt")).click();

        driver.findElement(By.id("btnclosepaxoption")).click();

        // 8. Static Dropdowns(Currencies)
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByValue("AED");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "AED");


        // 9. Checkbox Selections
        driver.findElements(By.xpath("//div[@id='discount-checkbox']//input[@type='checkbox']")).forEach(webElement -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            webElement.click();
        });
        driver.findElement(By.cssSelector("input[id*='friendsandfamily']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='friendsandfamily']")).isSelected());

        // 10. Search
        driver.findElement(By.xpath("//input[@value='Search' and @id='ctl00_mainContent_btn_FindFlights']")).click();


        Thread.sleep(3000);
        driver.quit();
    }
}
