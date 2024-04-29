package demo.POM;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrowserStackSignUpPage {
	WebDriver driver;
	By Header = By.xpath("//h1");
	By userName = By.xpath("//*[@id='user_full_name']");
	By businessEmail = By.xpath("//*[@id='user_email_login']");
	By password = By.xpath("//*[@id='user_password']");

	/*Commenting out this constructor would render the BrowserStackSignUpPage class 
	 * unusable unless you provide another way to initialize the driver variable,  
	 * which is essential for interacting with the web elements.*/
	
	
	public BrowserStackSignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public void veryHeader() {
		String getheadertext = driver.findElement(Header).getText().trim();
		assertEquals("Create a FREE Account", getheadertext);
	}

	public void enterFullName(String arg1) {
		driver.findElement(userName).sendKeys(arg1);
	}

	public void enterBusinessEmail(String arg1) {
		driver.findElement(businessEmail).sendKeys(arg1);
	}

	public void enterPasswrod(String arg1) {
		driver.findElement(password).sendKeys(arg1);
	}
}

/*Explanation of Code

Code Line-10 to 14: Identifying elements present on BrowserStack SignUp Page such as header and Get Started button
Code Line-20 to 35: Performing actions on identified objects on the BrowserStack SignUp Page*/