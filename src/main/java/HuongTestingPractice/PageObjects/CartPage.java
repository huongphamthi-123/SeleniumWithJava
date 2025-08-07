package HuongTestingPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HuongTestingPractice.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
		private WebDriver driver;
		
		public CartPage(WebDriver driver)
		{
			super(driver);
			this.driver =driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".cartSection h3") 
		private List<WebElement> cartProducts;
		
		@FindBy(css=".totalRow button") 
		private WebElement checkoutBtn;
		
		
		//private By result = By.cssSelector(".ta-results");
		// comment
		
		public Boolean verifyProductDisplay(String productName) {
			waitForWebElementToAppear(cartProducts);
			Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
		 
		public CheckoutPage checkout() {
			waitForElementToClickable(checkoutBtn);
			checkoutBtn.click();
			return new CheckoutPage(driver);
		}
		
		
}
