package com.selenium.amazon_automation.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager {
	
	public static ExtentReports generateReport(String reportName) {
		String reportPath = System.getProperty("user.dir") + "/extent_report/";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath + reportName + ".html");
		reporter.config().setReportName(reportName);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
}
