package com.thomas.web.pages.uploadAndDownload;

import com.thomas.pojo.excel.model.CellDetails;
import com.thomas.utils.DataDriven;
import com.thomas.utils.WaitHelper;
import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UploadAndDownloadPage {
    WebDriver uploadAndDownloadDriver;
    WaitHelper waitHelper;

    public UploadAndDownloadPage(WebDriver driver){
        this.uploadAndDownloadDriver = driver;
        waitHelper = new WaitHelper(uploadAndDownloadDriver,10);
        PageFactory.initElements(uploadAndDownloadDriver, this);
    }

    // REGION : PAGE LOCATORS
    @FindBy(css = "button#downloadButton")
    WebElement buttonDownload;

    @FindBy(css = "input#fileinput")
    WebElement buttonChooseFile;

    @FindBy(xpath = "(//div[normalize-space()='Updated Excel Data Successfully.'])[last()]")
    WebElement textSuccessPopup;

    public WebElement getObjectFruitDataByColumnIndex(String fruitName, String columnIndex){
        return uploadAndDownloadDriver.findElement(By.xpath(
                "//div[text()='"+fruitName+"']/parent::*/parent::*/div[@id='cell-"+columnIndex+"-undefined']/div"));
    }

    public WebElement getObjectTextColumn(String columnName){
        return uploadAndDownloadDriver.findElement(By.xpath("//div[text()='"+columnName+"']"));
    }
    // END REGION

    // REGION : PAGE METHODS
    public void clickButtonDownload(){
        waitHelper.waitForElementTobeVisible(buttonDownload);
        buttonDownload.sendKeys(Keys.chord(Keys.ENTER));
    }

    public void sendFile(String filePath){
        waitHelper.waitForElementTobeVisible(buttonChooseFile);
        buttonChooseFile.sendKeys(filePath);
    }

    public void verifySuccessUpload(String successMsg){
        waitHelper.waitForElementTobeVisible(textSuccessPopup);
        Assert.assertEquals(textSuccessPopup.getText(), successMsg);

        waitHelper.waitForElementTobeInvisible(textSuccessPopup);
    }

    public void editExcelData(String excelPath, String sheetName, String columName, String cellName, String valueToEdit){
        CellDetails cellDetails = DataDriven.getDetailsOfCell(excelPath, sheetName, cellName);

        DataDriven.editCellWithColumnNameAndRowIndex(
                excelPath, sheetName, cellDetails.rowIndex,  columName, valueToEdit);
    }

    public void retreiveFruitData(String fruitName, String columName, String expectedData){
        String columnIndex = getObjectTextColumn(columName).getAttribute("data-column-id");
        String actualData = getObjectFruitDataByColumnIndex(fruitName, columnIndex).getText();
        Assert.assertEquals(actualData, expectedData);
    }

    public void performUploadAndDownloadPage(
            boolean clickButtonDownload,
            String filePath,
            boolean successUpload,
            String successMsgToCheck){
        if(clickButtonDownload)
            this.clickButtonDownload();
        if(!StringUtils.isNullOrEmpty(filePath))
            this.sendFile(filePath);
        if(successUpload && !StringUtils.isNullOrEmpty(successMsgToCheck))
            this.verifySuccessUpload(successMsgToCheck);
    }
    // END REGION
}
