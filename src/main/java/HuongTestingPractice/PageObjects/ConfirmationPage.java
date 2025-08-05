package HuongTestingPractice.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HuongTestingPractice.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
		WebDriver driver;
		
		public ConfirmationPage(WebDriver driver)
		{
			super(driver);
			this.driver =driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".hero-primary") 
		WebElement successfulMsg;
		
		public Boolean VerifyConfirmMsg() {
			String confirmMessage = successfulMsg.getText();
			return confirmMessage.equalsIgnoreCase("Thankyou for the order.");
			
		}
}
