package com.thomas.web.practice.frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class DragAndDropTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        driver.findElement(By.cssSelector("div[id = draggable]")).click();

        Actions actions = new Actions(driver);
        WebElement source = driver.findElement(By.cssSelector("div[id = draggable]"));
        WebElement target = driver.findElement(By.cssSelector("div[id = 'droppable']"));
        actions.dragAndDrop(source, target)
                .build()
                .perform();

        driver.switchTo().defaultContent();

        Thread.sleep(3000);
        driver.quit();
    }
}
