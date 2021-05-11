package com.SeleniumLearn.testCases;


import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.SeleniumLearn.base.TestBase;
import com.SeleniumLearn.utilities.TestUtil;

public class OpenAccountTest extends TestBase{

	@Test(dataProviderClass = TestUtil.class,dataProvider = "dp")
	public void openAccountTest(String customer,String currency)
	{
		
	}
	

	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

}
