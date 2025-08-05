package HuongTestingPractice.PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import HuongTestingPractice.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
		WebDriver driver;
		
		public LandingPage(WebDriver driver)
		{
			super(driver);
			this.driver =driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(id="userEmail") 
		WebElement userEmail;
		
		@FindBy(id="userPassword") 
		WebElement userPassword;
		
		@FindBy(id="login") 
		WebElement submit;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;

		
		public ProductCatalogue loginApplication(String username, String pass) {
			waitForElementToClickable(userEmail);
			userEmail.sendKeys(username);
			
			waitForElementToClickable(userPassword);
			userPassword.sendKeys(pass);
			
			submit.click();
			return new ProductCatalogue(driver);
		}
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		public void goTo() throws IOException {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/HuongTestingPractice/Resources/GlobalData.properties");
			prop.load(fis);
			String url = prop.getProperty("url");
			driver.get(url);
		}
}
