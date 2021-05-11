package com.SeleniumLearn.testCases;
 
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.SeleniumLearn.base.TestBase;

public class BankManagerLoginTest extends TestBase{
@Test
	public void loginAsBankManager() 
	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		click(By.cssSelector(OR.getProperty("bmlBtn_CSS")));
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		log.debug("login done");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Login Successfully executed");
		//Assert.fail("login not successul");
	//	Reporter.log("<a href=\"D:\\q.jpg\">Screenshot</a>");
		
	}
	
}
