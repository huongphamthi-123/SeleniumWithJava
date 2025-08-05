package HuongTestingPractice.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import HuongTestingPractice.PageObjects.CartPage;
import HuongTestingPractice.PageObjects.ProductCatalogue;
import HuongTestingPractice.TestComponents.BaseTest;
import HuongTestingPractice.TestComponents.Retry;


public class ErrorValidation extends BaseTest{

	@Test(retryAnalyzer=Retry.class)
	public void  LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub

		//String productName = "ZARA COAT 3";
		 landingPage.loginApplication("Huong@gmail.com","Iamkingrr@000");
		Assert.assertEquals("Incorrect email o password.", landingPage.getErrorMessage());
		
	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}


}
 