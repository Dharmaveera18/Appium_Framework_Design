package org.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	AndroidDriver driver;
	public AppiumDriverLocalService service;

//	public AppiumUtils(AndroidDriver driver) {
//		this.driver = driver;
//		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//	}

	public Double getformattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1)); // ==> amountString.substring(1) ==> $160 ==>160
		return price;
	}

	

	public void waitforElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	}
	
	public AppiumDriverLocalService startAppiumServer(String IPAddress, int port)
	{
		  service = new AppiumServiceBuilder()
		           // .usingDriverExecutable(new File("C:\\Program Files\\nodejs"))  // Path to Node.js
		            .withAppiumJS(new File("/Users/dharmaveerah/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))  // Path to Appium's main.js
     	            .withIPAddress(IPAddress)
     	            .usingPort(port)
//		            .withIPAddress("127.0.0.1")  // Set IP address to 127.0.0.1
//		            .usingPort(4723)  // Port number
		            .build();
		  
		 // service.start();
		  
		  return service;
	}
}
