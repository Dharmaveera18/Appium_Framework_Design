package org.rahulshettyacademy.TestUtils;

import java.io.IOException;

import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {
	
	AppiumDriver driver;
	public static ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	  }
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	  }
	
	@Override
	public void onTestFailure(ITestResult result) {
	    test.fail(result.getThrowable()); //throws error message
	    try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	    
	    
	    try {
			test.addScreenCaptureFromPath(screenShotPath(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }
	
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
//	    onTestFailure(result);
	  }
	
	

	@Override
	public void onStart(ITestContext context) {
		    // not implemented
		  }
	
	
	@Override
	public void onFinish(ITestContext context) {
		    extent.flush();
		  }
	
	

}
