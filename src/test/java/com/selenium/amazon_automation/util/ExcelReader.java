package com.selenium.amazon_automation.util;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	
	public ExcelReader(String path) {
		super();
		try {
			workBook = new XSSFWorkbook(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getRowCount(String sheetName) {
		int idx = workBook.getSheetIndex(sheetName);
		
		if(idx == -1) {
			return 0;
		}else {
			this.sheet = workBook.getSheetAt(idx);
			return sheet.getPhysicalNumberOfRows();
		}
	}
	
	public int getColCount(String sheetName) {
		int idx = workBook.getSheetIndex(sheetName);
		
		if(idx == -1) {
			return 0;
		}else {
			this.sheet = workBook.getSheetAt(idx);
			return sheet.getRow(0).getLastCellNum();
		}
	}
	
	public String getCellValue(int row, int col) {
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(sheet.getRow(row).getCell(col));
	}
	
	public String[][] getData(String sheetName) {
		int row = getRowCount(sheetName);
		int col = getColCount(sheetName);
		
		String[][] data = new String[row-1][col];
		
		for(int i=1; i<row; i++) {
			for(int j=0; j<col; j++) {
				data[i-1][j] = getCellValue(i, j);
			}
		}
		
		return data;
	}
	
	public void close() {
		try {
			workBook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
