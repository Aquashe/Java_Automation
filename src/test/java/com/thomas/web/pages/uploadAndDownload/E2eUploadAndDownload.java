package com.thomas.web.pages.uploadAndDownload;

import com.thomas.web.base.BaseTest;
import org.testng.annotations.Test;

public class E2eUploadAndDownload extends BaseTest {
    String filePath = "C:\\Users\\Wonder\\Downloads\\download.xlsx";

    @Test
    public void performE2eUploadAndDownload(){
        launchApplication("https://rahulshettyacademy.com/upload-download-test/");

        String fruitName = "Apple";
        String columnNameTocheck = "Price";
        String columnValueToChange = "390";

        UploadAndDownloadPage uploadAndDownloadPage = new UploadAndDownloadPage(driver);

        uploadAndDownloadPage.editExcelData(filePath, "Sheet1", columnNameTocheck, fruitName, columnValueToChange);

        uploadAndDownloadPage.performUploadAndDownloadPage(false, filePath,
                true, "Updated Excel Data Successfully.");

        uploadAndDownloadPage.retreiveFruitData(fruitName, columnNameTocheck, columnValueToChange);
    }

}
