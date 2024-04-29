package demo.POM;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrowserStackHomePage {

	WebDriver driver; //Instance variable to hold the WebDriver instance.
	By Header = By.xpath("//h1"); // locator for the header element on the page.
	By getStarted = By.xpath("//*[@id='signupModalProductButton']");  //Locator for the "Get Started" button on the page.

	public BrowserStackHomePage(WebDriver driver) { //Constructor to initialize the WebDriver instance.
		this.driver = driver;
	}

	public void veryHeader() {
		String getheadertext = driver.findElement(Header).getText();
		assertEquals("App & Browser Testing Made Easy", getheadertext);
	}

	public void clickOnGetStarted() {
		driver.findElement(getStarted).click();
	}
}

/* Explanation of Code

Code Line-10 to 11: Identifying elements present on BrowserStack Home Page such as header and Get Started button
Code Line-17 to 24: Performing actions on identified objects on BrowserStack Home Page */