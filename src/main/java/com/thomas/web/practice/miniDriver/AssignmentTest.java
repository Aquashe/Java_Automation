package com.thomas.web.practice.miniDriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class AssignmentTest {

    WebDriver driver;
    WebElement option;

    @Test(priority = 1)
    public void chooseCheckBoxOption() {
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // 1. Choose the checkbox option
        WebElement optionCheckbox = driver.findElement(By.xpath("//input[@value = 'option2']"));
        option  = optionCheckbox.findElement(By.xpath("parent::label"));
        System.out.println("Option Selected : "+option.getText());
        optionCheckbox.click();
    }

    @Test(priority = 2)
    public void chooseDropdownBasedOnOption(){
        // 2.Choose Dropdown
        Select dropdown = new Select(driver.findElement(By.id("dropdown-class-example")));
        dropdown.selectByVisibleText(option.getText());

    }

    @Test(priority = 3)
    public void provdeAlertBasedOnOption() throws InterruptedException {
        // 3. Give Alert
        String optionSelected = option.getText();
        driver.findElement(By.id("name")).sendKeys(optionSelected);
        driver.findElement(By.id("alertbtn")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(optionSelected));
        alert.accept();

        Thread.sleep(3000);
        driver.quit();
    }
}
