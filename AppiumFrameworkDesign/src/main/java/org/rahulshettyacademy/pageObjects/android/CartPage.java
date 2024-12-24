package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	
    AndroidDriver driver;
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayTotalamtString;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsBtn;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeBtn;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedBtn;
	
	public List<WebElement> getProductList()
	{
		return productPrices;
	}
	
	public Double getProductsSum()
	{
		int count = productPrices.size();
		   Double totalsum = 0.0;
		   for(int i = 0; i<count; i++)
		   {
			  String amountString =  productPrices.get(i).getText();
			  
			  Double price = getformattedAmount(amountString);
			  totalsum = totalsum + price;  //160.97 + 120.00
		   }
		   
		   return totalsum;
	}
	
	public Double getTotalAmountDisplayed()
	{
		String DisplayTotalamtString = displayTotalamtString.getText();
		 Double DisplayTotalsum = getformattedAmount(DisplayTotalamtString);
		 return DisplayTotalsum;
	}
	
	public void acceptTerms() throws InterruptedException
	{
		longpressGesture(termsBtn);
		Thread.sleep(2000);
		closeBtn.click();
		checkBox.click();
		
	}
	
	
	public void submitForm()
	{
		proceedBtn.click();
	}
}
