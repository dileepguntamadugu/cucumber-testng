package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadExcelDataTestNG {
	@DataProvider
    public static Object[][] ReadVariant() throws IOException{
		FileInputStream fileInputStream= new FileInputStream(new File("TestData/Data.xlsx").getAbsoluteFile());
		DataFormatter formatter= new DataFormatter();
        Workbook workbook = WorkbookFactory.create(fileInputStream); 
        Sheet worksheet=workbook.getSheet("DataSheet");
        Row Row=worksheet.getRow(0); 
        int RowNum = worksheet.getPhysicalNumberOfRows();
        int ColNum= Row.getLastCellNum(); 
        Object Data[][]= new Object[RowNum-1][ColNum];
        for(int i=0; i<RowNum-1; i++){
        	Row row= worksheet.getRow(i+1);
            for (int j=0; j<ColNum; j++){
            	if(row==null) {
            		Data[i][j]= "";
            	}else{
            		Cell cell= row.getCell(j);
            		if(cell==null){
            			Data[i][j]= ""; 
            		}else{
            			String value=formatter.formatCellValue(cell);
            			Data[i][j]=value;
            		}
            	}
            }
         }
        return Data;
    }
}