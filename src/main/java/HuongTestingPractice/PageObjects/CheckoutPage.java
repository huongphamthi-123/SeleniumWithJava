package HuongTestingPractice.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HuongTestingPractice.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
		WebDriver driver;
		
		public CheckoutPage(WebDriver driver)
		{
			super(driver);
			this.driver =driver;
			PageFactory.initElements(driver, this);
		}
		
			
		@FindBy(css="[placeholder='Select Country']") 
		WebElement country;
		
		@FindBy(css=".action__submit") 
		WebElement submitBtn;
		
		@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]") 
		WebElement searchResult;
		
		
		By result = By.cssSelector(".ta-results");
		

		public void fillCountry(String filter) {
			Actions a = new Actions(driver);
			waitForElementToClickable(country);
			a.sendKeys(country,filter).build().perform();
			waitForElementToAppear(result);
			scrollIntoview( searchResult);
			searchResult.click();
		}
		
		public ConfirmationPage placeOrder() {
			scrollIntoview(submitBtn);
			waitForElementToClickable(submitBtn);
			submitBtn.click();
			return new ConfirmationPage(driver);
		}
		
}
