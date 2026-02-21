package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AssignmentTwoDropdowns {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.manage().window().maximize();

        //1.Name
        driver.findElement(By.xpath("//div[@class='form-group']//input[@name='name']")).sendKeys("Raj");
        //2.Email
        driver.findElement(By.xpath("//div[@class='form-group']//input[@name='email']")).sendKeys("raj@khna");
        //3.Password
        driver.findElement(By.xpath("//div[@class='form-group']//input[@type='password']")).sendKeys("Pass@1234");
        //4.Checkbox
        driver.findElement(By.xpath("//input[@id='exampleCheck1']")).click();
        //5.Gender
        Select genderOptions = new Select(driver.findElement(By.xpath("//select[@id='exampleFormControlSelect1']")));
        genderOptions.selectByVisibleText("Male");
        //6.Radio Buttons
        driver.findElement(By.cssSelector("#inlineRadio1")).click();
        //7.Date
        driver.findElement(By.xpath("//input[@name='bday' and @type='date']")).sendKeys("12-09-12");
        //8.Submit
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        //9.Check Success
        Assert.assertTrue(driver.findElement(
                By.cssSelector(".alert.alert-success.alert-dismissible")).getText().contains("Success! The Form has been submitted successfully!."));

        Thread.sleep(3000);
        driver.quit();
    }
}
