package com.selenium.amazon_automation.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ProductTestListener implements ITestListener {
	private ExtentReports extent;
	private ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(getTestMethodName(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test passed");
		logTestData(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test failed");
		logTestData(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test skipped");
		logTestData(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		test.log(Status.FAIL, "Test failed with timeout");
		logTestData(result);
	}

	@Override
	public void onStart(ITestContext context) {
		extent = ExtentReportsManager.generateReport("product_test_report");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private void logTestData(ITestResult result) {
		String[] dataKeys = { "full name", "phone number", "pincode", "address", "city", "state"};
		Object[] dataValues = result.getParameters();

		for (int i = 0; i < dataKeys.length; i++) {
			test.info(String.format("%s : %s", dataKeys[i], dataValues[i].toString()));
		}
	}

	private String getTestMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}
}
