package com.thomas.web.practice.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class AssignmentDropdown {
    public static void main(String []args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //Question 1
        driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).click();
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).isSelected());

        driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='checkBoxOption1']")).isSelected());

        //Question 2
        System.out.println("The number of checkboxes : "+driver.findElements(By.xpath("//div[@id='checkbox-example']//input[@type='checkbox']")).size());

        Thread.sleep(2000);
        driver.quit();
    }
}
