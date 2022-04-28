package Utility;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import Utility.ExcelReader;

public class DataUtil{
	
	public static ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	@DataProvider(name="dp1")
	public Object[][] getData(Method m) {
	//Method m is same as sheet name in excel so data provider
	//can differentiate which method requested data and from which sheet
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
			
		Object[][] data = new Object[rows-1][cols];
	
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			
			for(int colNum=0; colNum<cols;colNum++) {
				
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				
			}
			
		}
				
		return data;		
		
	}
	

}
