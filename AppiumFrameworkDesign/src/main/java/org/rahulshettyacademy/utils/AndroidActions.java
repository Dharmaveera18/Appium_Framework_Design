package org.rahulshettyacademy.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils{
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver)
	{
//		super(driver);
		this.driver = driver;
	}
	
	public void longpressGesture(WebElement element) throws InterruptedException
	{
		//To perform longpress
		
				((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
					    "elementId", ((RemoteWebElement) element).getId(),
					    "duration",2000
					));
				
				Thread.sleep(2000);
	}
	
	
	public void scrollTillSpecificWebElement(String searchText)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + searchText + "\"));"));
	}
	
	public void scrolluntillEndAction() throws InterruptedException
	{
		boolean canScrollMore;
		do {
			        canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 1.0
				));
			   Thread.sleep(1000);
		}
		while(canScrollMore);
	}
	
	
	public void swipeGesture(WebElement firstImage)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) firstImage).getId(),
			    "direction", "left",
			    "percent", 0.3
			));
	}
	
	public void dragDropGesture(WebElement source)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", 652,
			    "endY", 580
			));
	}

	
}
