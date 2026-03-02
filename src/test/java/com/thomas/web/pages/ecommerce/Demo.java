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

       String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\excel\\StudentData.xlsx";
       String sheetName = "StudentDetails";
       String columnName = "Name";
       List<String> columnNames = List.of("Name", "Age", "Gender");
        Map<String, List<Object>> data = Map.of(
                "BookName", List.of("Aryabhataa", "Shushruthi", "Ranghan", "Avehsam", "Fahad"),
                "Author", List.of("raman", "Setha", "Munni", "Parichayam", "Neha"),
                "Count", List.of(12, 12, 32, 12, 21));

        DataDriven.createExcelWithColumnAsHeaderFromMap(excelPath, "LibraryDetails", data);
    }
}
