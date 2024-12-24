package org.rahulshettyacademy.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Dharma");

	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/text1']")
	private WebElement countryDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitBtn;
	  
	
	
	public void setNameField(String name)
	{
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("female"))
		{
			femaleOption.click();
		}
		else
		{
			maleOption.click();
		}
	}
	
	public void setActivity()
	{
         Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
			));
	}
	
	public void setCountrySelection(String countryName)
	{
		countryDropdown.click();
		scrollTillSpecificWebElement(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='"+countryName+"']")).click();
		
	}
	
	public ProductsCatalogPage submitForm()
	{
		submitBtn.click();
		return new ProductsCatalogPage(driver);
	}
}
