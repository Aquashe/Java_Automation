package com.thomas.web.practice.frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DragAndDropAssignment {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name = 'frame-top']")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name = 'frame-middle']")));

        System.out.println("Text : " + driver.findElement(By.id("content")).getText());

        Thread.sleep(3000);
        driver.quit();
    }
}
