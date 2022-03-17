package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.PublicKey;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtilityJava {

	/**
	 * This method will read data from excel sheet and return value when sheetname,rownumber and cell number is specified
	 * @param SheetName
	 * @param rolNo
	 * @param celNo
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String SheetName, int rowNo,int celNo) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.getCell(celNo);
		String value = cel.getStringCellValue();
		return value;  
	}
	
	/**
	 * This method will read date-data from excel sheet and return string value when sheetname,rownumber and cell number is specified
	 * @param SheetName
	 * @param rowNo
	 * @param celNo
	 * @return
	 * @throws Throwable
	 */
	public String readDateFromExcel(String SheetName, int rowNo,int celNo) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.getCell(celNo);
		String value = cel.getStringCellValue();
		return value.toString();  
	}
	/**
	 * This method will read data(numeric value) from excel sheet and return string value when sheetname,rownumber and cell number is specified
	 * @param rowNo
	 * @param celNo
	 * @param SheetName
	 * @return
	 * @throws Throwable
	 */
	public String readNumDataFromExcel(String SheetName, int rowNo,int celNo) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		Row ro = sh.getRow(rowNo);
		Cell cel = ro.getCell(celNo);
		double CELLVALUE = cel.getNumericCellValue();
		int CV=(int)CELLVALUE;
		Integer integer=CV;
		String SV=Integer.toString(integer);
		return SV;  
	}
	
	
		
	/**
	 * This method will write data into excel sheet and return value when sheetname,rownumber, cell number and value is specified
	 * @throws Throwable 
	 */
		public void writeDataIntoExcel(String SheetName,int rowNo, int celNo,String value) throws Throwable {
			FileInputStream fi=new FileInputStream(IpathConstants.ExcelPath);
			Workbook wb = WorkbookFactory.create(fi);
			Sheet sh = wb.getSheet(SheetName);
			Row ro=sh.getRow(rowNo);
			Cell cel=ro.createCell(celNo);
			cel.setCellValue(value);
			FileOutputStream fos= new FileOutputStream(IpathConstants.ExcelPath);
			wb.write(fos);
		}
		
		public int getRowCount(String SheetName) throws Throwable {
			FileInputStream fis=new FileInputStream(IpathConstants.ExcelPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(SheetName);
			int lastrow = sh.getLastRowNum();
			return lastrow;
		}
		
		
		public Object[][] readmultipleDataFromExcel(String SheetName, int m) throws Throwable
		{
			FileInputStream fis = new FileInputStream(IpathConstants.ExcelPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(SheetName);
			int lastRow = sh.getLastRowNum();
			int lastCell = sh.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[lastRow][lastCell-m];
			for(int i = 0;i<lastRow;i++)
			{
				for(int j=0;j<lastCell-m;j++)
				{
					data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
				}
			}
			return data;
		
		}

		
		
}
