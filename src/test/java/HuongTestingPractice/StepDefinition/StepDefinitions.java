package HuongTestingPractice.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import HuongTestingPractice.PageObjects.CartPage;
import HuongTestingPractice.PageObjects.CheckoutPage;
import HuongTestingPractice.PageObjects.ConfirmationPage;
import HuongTestingPractice.PageObjects.LandingPage;
import HuongTestingPractice.PageObjects.ProductCatalogue;
import HuongTestingPractice.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
		// code
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCart();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.fillCountry("india");
		confirmationPage = checkoutPage.placeOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		Boolean confirmMessage = confirmationPage.VerifyConfirmMsg();
		// getScreenshot("submitOrder");
		Assert.assertTrue(confirmMessage, "Confirmation message does not match");
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
