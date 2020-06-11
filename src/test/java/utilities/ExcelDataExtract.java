package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataExtract {
	private static List<String> headerList;
	private static final int HEADERROW = 0;
	
	public static void main(String[] args) {
		ExcelDataExtract ede = new ExcelDataExtract();
		headerList = new ArrayList<>();
		File file = new File("TestData/Data.xlsx");
		System.out.println("Data File Path is: "+file.getAbsolutePath());
		Sheet regressionSheet = ede.getWorkBook(file.getAbsolutePath(), "DataSheet");
		ede.getSheetData(regressionSheet, ede.getHeaderList(regressionSheet));
	}
	
	public Sheet getWorkBook(String fileName, String sheetName) {
		Sheet objSheet = null;
		try(InputStream fin = new FileInputStream(fileName)){
			Workbook wbObject = WorkbookFactory.create(fin);
			objSheet = wbObject.getSheet(sheetName);
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return objSheet;
	}
	
	public List<String> getHeaderList(Sheet sheetObject) {
		Row rows = sheetObject.getRow(HEADERROW);
		for(Cell cell: rows) {
			headerList.add(cell.getStringCellValue());
		}
		return headerList;
	}
	
	public Collection<Object[]> getSheetData(Sheet sheetObject, List<String> headerList) {
		int rowCounter = 0;
		Collection<Object[]> rowObjects = new ArrayList<>();
		for(Row row: sheetObject){
			if(rowCounter!=HEADERROW) {
				System.out.println("RowNo: "+rowCounter+" || Data: "+getRowData(headerList, row));
				rowObjects.add(new Object[] {getRowData(headerList,row)});
			}
			rowCounter++;
		}
		return rowObjects;
	}
	
	public Map<String, Object> getRowData(List<String> headerList, Row rowObject) {
		HashMap<String, Object> rowDataMap = new HashMap<>();
		int headerListCounter = 0;
		for(Cell cell: rowObject) {
			rowDataMap.put(headerList.get(headerListCounter), getCellTypeDataValue(cell));
			headerListCounter++;
		}
		return rowDataMap;
	}
	
	public Object getCellTypeDataValue(Cell cell) {
		CellType type = cell.getCellType();
		Object obj = null;
		if(type.equals(CellType.STRING)) {
			obj = cell.getStringCellValue();
		}else if(type.equals(CellType.NUMERIC)) {
			obj = cell.getNumericCellValue();
		}else if(type.equals(CellType.BOOLEAN)) {
			obj = cell.getBooleanCellValue();
		}else if(type.equals(CellType.BLANK)) {
			obj = "";
		}
		return obj;
	}
}