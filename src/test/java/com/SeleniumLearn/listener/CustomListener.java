package com.SeleniumLearn.listener;

import org.testng.ITestListener ;		
import org.testng.ITestResult ;
import org.testng.Reporter;

import com.SeleniumLearn.utilities.ExtentManager;
import com.SeleniumLearn.utilities.TestUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext ;

public class CustomListener implements ITestListener {
	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String messageBody;
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
 	
	
	    public void onFinish(ITestContext arg0) {					
	        // TODO Auto-generated method stub
	    	if (extent != null) {

				extent.flush();
			}
	    	Reporter.log("test case finish");
	    }		

	    		
	    public void onStart(ITestContext arg0) {					
	        // TODO Auto-generated method stub		
	
	    }		

	    		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
//	        		
	    }		

//	    		
	    public void onTestFailure(ITestResult result) {					
	    	String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
			testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
					+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
			
			try {

				TestUtil.captureScreenshot();
				testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
						MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
								.build());
			} catch (IOException e) {

			}
			
			String failureLogg="TEST CASE FAILED";
			Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
			testReport.get().log(Status.FAIL, m);
	        Reporter.log("test case failed"+TestUtil.screenshotPath);	
	        Reporter.log("<a href="+TestUtil.screenshotPath+">Screenshot</a>");
	    }		

	    		
	    public void onTestSkipped(ITestResult result) {					
	        // TODO Auto-generated method stub				
	    	String methodName=result.getMethod().getMethodName();
			String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			testReport.get().skip(m);
	    }		

	    		
	    public void onTestStart(ITestResult result) {					
	        // TODO Auto-generated method stub				
			ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
	        testReport.set(test);
	    	Reporter.log("test case start");	
	    }		

	    		
	    public void onTestSuccess(ITestResult result) {					
	        // TODO Auto-generated method stub			
			String methodName=result.getMethod().getMethodName();
			String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			testReport.get().pass(m);
	    	Reporter.log("test case Passed");	
	        Reporter.log("<a href=\"D:\\q.jpg\">Screenshot</a>");
	        		
	    }		

}
