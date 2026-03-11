package com.thomas.web.pages.ecommerce;

import com.thomas.pojo.excel.model.CellDetails;
import com.thomas.reporting.ExecutionManager;
import com.thomas.utils.DataDriven;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Demo {

    @Test
    public void demo(){

       String excelPath =
               System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\excel\\StudentData.xlsx";
       String sheetName = "LibraryDetails";
       String columnName = "BookName";
       Map<String, List<Object>> data = DataDriven.getExcelSheetDetailsWithColumnAsHeader(
               excelPath,sheetName, List.of("Count", "BookName", "Author"));
       for(String key : data.keySet())
           System.out.println(key+" : "+data.get(key));

       DataDriven.editCellWithColumnNameAndRowIndex(
               excelPath, sheetName, 3,  "BookName", "paripari");

        Map<String, List<Object>> data2 = DataDriven.getExcelSheetDetailsWithColumnAsHeader(
                excelPath,sheetName, List.of("Count", "BookName", "Author"));
        for(String key : data2.keySet())
            System.out.println(key+" : "+data2.get(key));


    }
}
