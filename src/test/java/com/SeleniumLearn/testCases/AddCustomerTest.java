package com.SeleniumLearn.testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.SeleniumLearn.base.TestBase;
import com.SeleniumLearn.utilities.TestUtil;

public class AddCustomerTest extends TestBase{

	@Test(dataProviderClass = TestUtil.class,dataProvider = "dp")
	public void addCustomerTest(String firstName,String lastName,String postCode,String alertText)
	{
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstname_CSS"))).sendKeys(firstName);;
		driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(lastName);;
		driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(postCode);;
		driver.findElement(By.cssSelector(OR.getProperty("addbtn_CSS"))).click();
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();  
		
	}

	@DataProvider
	public Object[][] getData() {

		//String sheetName = m.getName();
		String sheetName="AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];
		
//		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

//			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
//				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}

		}

		return data;

	}

}
