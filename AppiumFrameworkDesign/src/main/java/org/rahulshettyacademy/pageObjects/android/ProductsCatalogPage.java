package org.rahulshettyacademy.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsCatalogPage extends AndroidActions{

     AndroidDriver driver;
	
	public ProductsCatalogPage(AndroidDriver driver)
	{
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement CartIcon;
	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
	}
	
	public CartPage gotoCartPage()
	{
		CartIcon.click();
		return new CartPage(driver);
	}
	
	
	
	
}
