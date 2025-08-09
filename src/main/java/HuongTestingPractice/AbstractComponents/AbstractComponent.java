package HuongTestingPractice.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import HuongTestingPractice.PageObjects.CartPage;
import HuongTestingPractice.PageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver =driver;
	}

	@FindBy(css="[routerlink*='cart']") 
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']") 
	WebElement OrderHeader;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement e) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(e));

	}
	public void waitForWebElementToAppear(List<WebElement> e) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(e));

	}
	
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(element));
		
		Thread.sleep(1000);
	}
	
	public void waitForElementToClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage goToCart() {
		waitForElementToClickable(cartHeader);
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrderPage() {
		OrderHeader.click();
		return new OrderPage(driver);
	}
	
	public void scrollIntoview(WebElement e)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", e);
	}
}
