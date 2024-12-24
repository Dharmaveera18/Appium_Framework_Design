package org.rahulshettyacademy.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.zip.InflaterInputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulshettyacademy.pageObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils{
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;
	
	
	@BeforeClass
	public void configAppium() throws InterruptedException, URISyntaxException, IOException
	{
		
		         Properties prop = new Properties();
		         FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//org//rahulshettyacademy//resources//data.properties");
		         prop.load(fis);
		         
		         
		         String IPAdress = prop.getProperty("IPAdress");
		         String port = prop.getProperty("port");
		
		       //How to start appium server programmatically
		         service = startAppiumServer(IPAdress, Integer.parseInt(port));
				 Thread.sleep(10000);
				// service.start();
				
				 Thread.sleep(10000);
				 UiAutomator2Options options = new UiAutomator2Options();
				 options.setChromedriverExecutable("/Users/dharmaveerah/Chrome_Drivers/chromedriver_win32/chromedriver.exe");
				 
				 //options.setDeviceName("myavd1");
				 //options.setDeviceName("Rahulemulator1");
				  //options.setDeviceName("Medium_AVD");
				  options.setDeviceName("MyNewAVD");
				 Thread.sleep(10000);
				 options.setApp(System.getProperty("user.dir") + "//src//test//java//org//rahulshettyacademy//resources//General-Store.apk");
				 //options.setApp("/Users/dharmaveerah/eclipse-workspace/Appium/src/test/java/resources/ApiDemos-debug.apk");
				 Thread.sleep(10000);
				
				//For Android apps ==> object of AndroidDriver
//				 driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
				 driver = new AndroidDriver(service.getUrl(), options);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				 formPage = new FormPage(driver);
				 //driver.pressKey(new KeyEvent(AndroidKey.BACK));
				 //driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException
	{
		//System.getProperty("user.dir") + "//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"
		//Convert json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8); 
//		
		//Convert json string to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
		{});
		return data;
				
	
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
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ searchText +"\"));"));
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
	
	public Double getformattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1)); // ==> amountString.substring(1) ==> $160 ==>160
	    return price; 
	}
	
	@AfterClass
	public void tearDown()
	{
		//How to stop appium server programmatically
		driver.quit();
		service.stop();
		
	}

}
