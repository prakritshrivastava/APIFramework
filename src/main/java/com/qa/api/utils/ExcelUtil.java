package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.IOException;

//Remember all imports should be from the ss user model.
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil {
	
	private static String TEST_DATA_SHEET_PATH="./src/test/resources/testdata/APITestData.xlsx";
	
	
	public static Object[][] readDatafromExcel(String sheetName) {
		
		Object[][] data = null;
		
		try {
			FileInputStream inputStream = new FileInputStream(TEST_DATA_SHEET_PATH);	
			
			//Workbook reference
			Workbook book =WorkbookFactory.create(inputStream);
			Sheet sheet=book.getSheet(sheetName);
			
			//Below last active row method is available but for column no such method is available , hence we go to the 0th row and then go the last active cell.
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int row=0;row<sheet.getLastRowNum();row++) {
				for(int col=0;col<sheet.getRow(0).getLastCellNum();col++) {
					//row + 1 taken because we want to skip reading the header 
					data[row][col]=sheet.getRow(row+1).getCell(col).toString(); 	
				}
			}
			
		}
		catch(IOException ioExp) {
			ioExp.printStackTrace();
		}
		
		return data;
		
	}
	
	

}
