package com.SeleniumLearn.rough;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TestDate {

	public static void main(String[] args) {
		Date d = new Date();
		String screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		String screenshotPath = System.getProperty("user.dir")  + screenshotName;
		System.out.println(screenshotName);
		System.out.println(screenshotPath);
		
		//FileUtils.copyFile(scrFile, new File(screenshotPath));
	}
}
