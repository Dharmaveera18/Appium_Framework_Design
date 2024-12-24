package org.rahulshettyacademy;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.rahulshettyacademy.TestUtils.BaseTest;
import org.rahulshettyacademy.TestUtils.ExtentReporterNG;
import org.rahulshettyacademy.TestUtils.Listeners;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
public class Ecommerce_tc_1 extends BaseTest {
	
	
	
	ExtentTest test=Listeners.test;
	
	@BeforeMethod
	public void preSetUp()
	{
		
		//adb shell dumpsys window | find "mCurrentFocus"
		//com.androidsample.generalstore/com.androidsample.generalstore.MainActivity
         Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
			));
	}
	
	
	
	
	@Test(priority = 2)
	public void fillForm_ErrorValidation() throws MalformedURLException, URISyntaxException, InterruptedException
	{
		
       //test.info("Validating the error message .. ");
	   Thread.sleep(2000);
	   //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dharma");
	   //driver.hideKeyboard();
	   driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	   driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']")).click();
	   String value = "Argentina";
	   scrollTillSpecificWebElement(value);
	   driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Argentina']")).click();
	   driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	   String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	   Assert.assertEquals(toastMessage, "Please enter your name");
	 // test.pass("Successfully validated error message ");
	}
	
	@Test(priority = 1)
	public void fillForm_PositiveFlow() throws MalformedURLException, URISyntaxException, InterruptedException
	{
	   Thread.sleep(2000);
	   driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dharma");
	   driver.hideKeyboard();
	   driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	   driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1']")).click();
	   String value = "Argentina";
	   scrollTillSpecificWebElement(value);
	   driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Argentina']")).click();
	   driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	   Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
	}

}
