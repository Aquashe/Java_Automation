package com.thomas.web.practice.calender;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class CalenderTest {

    WebDriver driver;
    Set<String> windows;
    WebElement topNavCalender;
    boolean chooseYearWindow;
    boolean chooseYearPair;
    String date = "09/02/2200";
    WebDriverWait wait ;

    @Test(priority = 1)
    public void clickTopDeals() {
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        // 1.Click on Top Deals
        driver.findElement(By.xpath("//a[text()='Top Deals']")).click();
    }

    @Test(priority = 2)
    public void switchToNewWindow() {
        // 2. Give ctrl to new window
        windows = driver.getWindowHandles();
        String childWindow = windows.stream().reduce((a, b) -> b).orElse("empty");
        driver.switchTo().window(childWindow);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(By.cssSelector("label[for='deliveryDate']"))));
    }

    @Test(priority = 3)
    public void chooseCalender() throws InterruptedException {
        // 3. Click Calender Icon
        driver.findElement(By.cssSelector(
                "button[class='react-date-picker__calendar-button react-date-picker__button']")).click();

        Thread.sleep(3000);
    }

    @Test(priority = 4)
    public void chooseYear() throws InterruptedException {
        String year = date.split("/")[2];
        topNavCalender = driver.findElement(By.xpath(
                "//button[@class='react-calendar__navigation__label']"));

        for (int i = 0; i < 3; i++)
            topNavCalender.click();

        Thread.sleep(2000);

        // Choose Year window
        chooseYearWindow = false;
        while (!chooseYearWindow) {
            List<WebElement> yearPairs = driver.findElements(By.cssSelector(
                    "div[class='react-calendar__century-view__decades'] button"));

            String first = yearPairs.stream()
                    .findFirst()
                    .map(WebElement::getText)
                    .map(s -> s.substring(0, 4))
                    .orElse("Not found First ");

            String last = yearPairs.reversed().stream()
                    .findFirst()
                    .map(WebElement::getText)
                    .map(s -> s.substring((s.length() - 4), s.length()))
                    .orElse("Not found Last");

            chooseLeftRightArrow(year, first, last).ifPresent(WebElement::click);
        }



        chooseYearPair = false;
        List<WebElement> yearPairsNew = driver.findElements(By.cssSelector(
                "div[class='react-calendar__century-view__decades'] button"));
        Optional<WebElement> yearClick = Optional.empty();

        int i=0;
        while(i<yearPairsNew.size() && !chooseYearPair){
            WebElement yearPair = yearPairsNew.get(i);
            chooseLeftRightYearPair(year, yearPair);
            i++;
        }

        // Click Year
        System.out.println("Click Year :"+year);
        driver.findElement(By.xpath(
                        "//button[@class='react-calendar__tile react-calendar__decade-view__years__year' and text()='"+year+"']"))
                .click();

        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void chooseMonth() throws InterruptedException {
        String  monthPart = date.split("/")[1];
        String cleanMonth = monthPart.replace("0", "");
        String month  = Month.of(Integer.parseInt(cleanMonth)).getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        // Choose Month
        System.out.println("Choose Month :"+month);
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("//*[text()='"+month+"']")))).click();

        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void chooseDay() throws InterruptedException {
        String  dayPart = date.split("/")[0];
        String cleanDay = dayPart.replace("0", "");

        // Choose Day
        System.out.println("Choose Day :"+cleanDay);
        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("(//abbr[text()='"+cleanDay+"'])[1]")))).click();

        Thread.sleep(2000);
    }

    @Test(priority = 7)
    public void checkDateDisplayed() throws InterruptedException {
        String dateTextHtml = driver.findElement(By.xpath(
                "//input[@name = 'date']")).getAttribute("value");
        String [] dateTextHtmlArray = dateTextHtml.split("-");
        String dateToCompare = String.format("%s/%s/%s",
                dateTextHtmlArray[2],dateTextHtmlArray[1],dateTextHtmlArray[0]);

        Assert.assertEquals(dateToCompare, date);

        Thread.sleep(2000);
        driver.quit();
    }


    private Optional<WebElement> chooseLeftRightArrow(String input, String first, String second) {
        int i = Integer.parseInt(input);
        int f = Integer.parseInt(first);
        int l = Integer.parseInt(second);

        if (i >= f && i <= l) {
            chooseYearWindow = true;
            return Optional.empty();
        } else if (i > f)
            return Optional.of(driver.findElement(By.xpath(
                    "//button[@class='react-calendar__navigation__arrow react-calendar__navigation__next-button']")));
        else if (i < f)
            return Optional.of(driver.findElement(By.xpath(
                    "//button[@class='react-calendar__navigation__arrow react-calendar__navigation__prev-button']")));
        else
            return Optional.empty();
    }

    private void chooseLeftRightYearPair(String input, WebElement yearElement){
        String firstYear = yearElement.getText().substring(0,4);
        String yearLast = yearElement.getText().substring(
                (yearElement.getText().length()-4),yearElement.getText().length());

        int i = Integer.parseInt(input);
        int f = Integer.parseInt(firstYear);
        int l = Integer.parseInt(yearLast);

        if (i >= f && i <= l) {
            chooseYearPair = true;
            yearElement.click();
        }
    }

}
