package HuongTestingPractice.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import HuongTestingPractice.PageObjects.CartPage;
import HuongTestingPractice.PageObjects.CheckoutPage;
import HuongTestingPractice.PageObjects.ConfirmationPage;
import HuongTestingPractice.PageObjects.OrderPage;
import HuongTestingPractice.PageObjects.ProductCatalogue;
import HuongTestingPractice.TestComponents.BaseTest;


public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="GetData",groups= {"ErrorHandling"})
	public void  submitOrder(HashMap<String,String> input ) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match, "Product does not match");
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.fillCountry("india");
		ConfirmationPage confirmationPage =checkoutPage.placeOrder();
		Boolean confirmMessage = confirmationPage.VerifyConfirmMsg();
		//getScreenshot("submitOrder");
		Assert.assertTrue(confirmMessage, "Confirmation message does not match");
	
	}
	
	@DataProvider
	public Object[][] GetData() throws IOException
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "Huong@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("product", "ZARA COAT 3");
		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "Huong@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ZARA COAT 3");
	
		List<HashMap<String,String>> map=	getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/HuongTestingPractice/Data/PurchaseOrder.Json");
		return new Object[][] {{map.get(0)}, {map.get(1)}};
		
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("Huong@gmail.com","Iamking@000");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyProductDisplay(productName));
	}
	
//	public File getScreenshot(String testCaseName) throws IOException
//	{
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File file = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
//		FileUtils.copyFile(source, file);
//		return file;
//	}
}
