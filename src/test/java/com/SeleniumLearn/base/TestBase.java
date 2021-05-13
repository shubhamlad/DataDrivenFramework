package com.SeleniumLearn.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.SeleniumLearn.listener.CustomListener;
import com.SeleniumLearn.utilities.ExcelReader;
import com.aventstack.extentreports.Status;



public class TestBase {
	
	public static WebDriver driver;

	public static Properties config= new Properties();
	public static Properties OR= new Properties();
	public static FileInputStream fis;	
	public static Logger log =Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel= new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	
	@BeforeSuite
	public void setUP() throws IOException
	{
		fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		log.debug("config done");
		fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		
		if(System.getenv("browser").equals("chrome"))
//		if(config.getProperty("browser").equals("chrome")) adddtest
		{
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			System.out.println(System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver= new ChromeDriver();
			log.debug("Chome launch");
	
		}
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 5);
	}
	
	public boolean isElementPresent(By by) {
		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}
	
	public void click(By by) {
		driver.findElement(by).click();
		CustomListener.testReport.get().log(Status.INFO, "Clicking on : " );
	}

	@AfterSuite
	public void tearDow()
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}
	
}
