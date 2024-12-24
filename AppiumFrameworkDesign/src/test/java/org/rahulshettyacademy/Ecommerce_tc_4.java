package org.rahulshettyacademy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import org.rahulshettyacademy.TestUtils.BaseTest;
import org.rahulshettyacademy.pageObjects.android.CartPage;
import org.rahulshettyacademy.pageObjects.android.ProductsCatalogPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class Ecommerce_tc_4 extends BaseTest {
	
	
	@Test(dataProvider = "getData")
	public void fillForm(HashMap<String,String> input) throws MalformedURLException, URISyntaxException, InterruptedException
	{
		
	   
	   formPage.setNameField(input.get("name"));
	   formPage.setGender(input.get("gender"));
	   formPage.setCountrySelection(input.get("country"));
	   ProductsCatalogPage ProdPage = formPage.submitForm();
	   Thread.sleep(2000);
	   ProdPage.addItemToCartByIndex(0);
	   ProdPage.addItemToCartByIndex(0);
	   CartPage cartPage = ProdPage.gotoCartPage();
	   Thread.sleep(2000);
	   
	   Double totalsum = cartPage.getProductsSum();
	   Double DisplayTotalsum =  cartPage.getTotalAmountDisplayed();
	   System.out.println(DisplayTotalsum);
	   Assert.assertEquals(totalsum, DisplayTotalsum);
	   cartPage.acceptTerms();
	   cartPage.submitForm();
	   
	   
//	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	   wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
	   

//	  Set<String> contexts = driver.getContextHandles();
//	   
//	   for( String ContextName : contexts)
//	   {
//		   System.out.println(ContextName);
//	   }
//	   //To move from native app to web app
//	   driver.context("WEBVIEW_com.androidsample.generalstore");
//	   driver.findElement(By.name("q")).sendKeys("Rahul shetty academy");
//	   driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//	   driver.pressKey(new KeyEvent(AndroidKey.BACK));
//	   //
//	   driver.context("NATIVE_APP");
//	   
	}
	
	@BeforeMethod
	public void preSetUp()
	{
		formPage.setActivity();
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
	    List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//org//rahulshettyacademy//testData//eCommerce.json");
		//return new Object[][] {{"Rahul shetty", "female", "Argentina"}, {"Rahul MR", "male", "Argentina"}};
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
