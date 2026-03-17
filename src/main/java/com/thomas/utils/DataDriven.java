    package com.thomas.utils;

    import com.thomas.exceptions.ExcelException;
    import com.thomas.pojo.excel.model.CellDetails;
    import io.cucumber.java.it.Ma;
    import lombok.extern.slf4j.Slf4j;
    import org.apache.poi.ss.usermodel.*;
    import org.apache.poi.ss.util.CellRangeAddressBase;
    import org.apache.poi.xssf.usermodel.XSSFSheet;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Slf4j
    public class DataDriven {

        private static XSSFSheet fetchSheetDataFromExcel(String excelPath, String sheetName){
            log.info("Reading Data from Sheet : {}  from Excel : {}",sheetName, excelPath);
            XSSFSheet sheet = null;
            try (FileInputStream fs = new FileInputStream(excelPath)){
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fs);
                int sheets = xssfWorkbook.getNumberOfSheets();
                for(int i=0; i<sheets; i++){
                    if(xssfWorkbook.getSheetName(i).equalsIgnoreCase(sheetName)){
                        sheet = xssfWorkbook.getSheetAt(i);
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                throw new ExcelException(String.format("Excel not found under the path : %s", excelPath), e);
            } catch (IOException e) {
                throw new RuntimeException(String.format("Failed to fetch data from Excel under the path : %s", excelPath), e);
            }
            return sheet;
        }

        public static CellDetails getDetailsOfCell(String excelPath, String sheetName, String columnName){
            XSSFSheet sheet= fetchSheetDataFromExcel(excelPath, sheetName);
            log.info("Getting ColumnInformation from Sheet : {}  for Column : {}",sheetName, columnName);
            for(Row row : sheet){
                for(Cell cell : row){
                    if(cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(columnName)){
                        CellDetails cellDetails = new CellDetails();
                        cellDetails.rowIndex = cell.getRowIndex();
                        cellDetails.columnIndex = cell.getColumnIndex();
                        cellDetails.row = row;
                        cellDetails.cell = cell;
                        return cellDetails;
                    }
                }
            }
            throw new ExcelException(String.format("Column named : %s not present inside the sheet : %s", columnName, sheet.getSheetName()));

        }

        private static Object getCellValue(Cell cell){
            if(cell == null)
                return null;
            switch (cell.getCellType()){
                case STRING :   return cell.getStringCellValue();
                case NUMERIC:   if(DateUtil.isCellDateFormatted(cell))
                    return  cell.getDateCellValue();
                    return cell.getNumericCellValue();
                case BOOLEAN:   return cell.getBooleanCellValue();
                case FORMULA:   return cell.getCellFormula();
                case BLANK:     return null;
                default:        return null;
            }
        }

        public static List<Object> getCellValuesByColumnAsHeader(String excelPath, String sheetName, String columnName){
            XSSFSheet sheet = fetchSheetDataFromExcel(excelPath, sheetName);
            CellDetails columnHeader = getDetailsOfCell(excelPath, sheetName, columnName);
            int columnNUmber = columnHeader.columnIndex;
            List<Object> cellValues = new ArrayList<>();
            for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++){
                Cell cell = sheet.getRow(i).getCell(columnHeader.columnIndex);
                cellValues.add(getCellValue(cell));
            }
            return cellValues;
        }

        public static Map<String, List<Object>> getExcelSheetDetailsWithColumnAsHeader(String excelPath, String sheetName,List<String> columNames){
            Map<String, List<Object>> excelSheetData = new HashMap<>();
            columNames.forEach(columName-> excelSheetData.put(columName,getCellValuesByColumnAsHeader(excelPath, sheetName, columName)));
            return excelSheetData;
        }

        public static void createExcelWithColumnAsHeaderFromMap(
                String excelPath, String sheetName, Map<String, List<Object>> data){
            try(XSSFWorkbook workbook = new XSSFWorkbook()){
                XSSFSheet sheet = workbook.createSheet(sheetName);

                Row headerRow = sheet.createRow(0);
                int columnHeader = 0;
                for(String header: data.keySet()){
                    Cell cell = headerRow.createCell(columnHeader++);
                    cell.setCellValue(header);
                }

                int rowCount = data.values().iterator().next().size();
                for(int i=1; i <= rowCount; i++){
                    Row row = sheet.createRow(i);
                    int columnIndex = 0;
                    for(String header: data.keySet()){
                        Cell cell = row.createCell(columnIndex++);
                        Object value = data.get(header).get(i-1);
                        if (value instanceof String)
                            cell.setCellValue((String) value);
                        else if (value instanceof Integer)
                            cell.setCellValue((Integer) value);
                        else if (value instanceof Double)
                            cell.setCellValue((Double) value);
                        else if (value instanceof Boolean)
                            cell.setCellValue((Boolean) value);
                        else
                            cell.setCellValue(value.toString());
                    }

                }

                try (FileOutputStream fos = new FileOutputStream(excelPath)){
                    workbook.write(fos);
                }
            } catch (IOException e) {
                throw new ExcelException(String.format("Error creating Excel file : %s", excelPath), e);
            }
        }

        public static void editCellWithColumnNameAndRowIndex(
                String excelPath, String sheetName,int rowIndex, String columnName, Object value){

            try(FileInputStream fileInputStream = new FileInputStream(excelPath)) {
                XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
                XSSFSheet sheet = workbook.getSheet(sheetName);

                CellDetails cellDetails = getDetailsOfCell(excelPath, sheetName, columnName);
                int columnIndex = cellDetails.columnIndex;
                Row row = sheet.getRow(rowIndex);

                Cell cell = row.getCell(columnIndex);
                log.info("Editing Cell at position ( {} , {})", rowIndex, columnIndex);
                cell = row.createCell(columnIndex);
                if (value instanceof String)
                    cell.setCellValue((String) value);
                else if (value instanceof Integer)
                    cell.setCellValue((Integer) value);
                else if (value instanceof Double)
                    cell.setCellValue((Double) value);
                else if (value instanceof Boolean)
                    cell.setCellValue((Boolean) value);
                else
                    cell.setCellValue(value.toString());
                try(FileOutputStream fileOutputStream = new FileOutputStream(excelPath)) {
                    workbook.write(fileOutputStream);
                }
            }catch (Exception e) {
                throw new ExcelException(String.format("Error editing Excel file : %s", excelPath), e);
            }

        }
    }
