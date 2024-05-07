package browserStackTC;

import java.io.IOException;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Baseclass.RetryAnalyzer;
import Baseclass.baseclass;
import demo.POM.BrowserStackHomePage;
import demo.POM.BrowserStackSignUpPage;

public class BrowserStackSetup extends baseclass {
	// BrowserStackHomePage objBrowserStackHomePage;
	// BrowserStackSignUpPage objBrowserStackSignUpPage;
	@BeforeTest
	public void setup() throws IOException {
		ExtentReports extent = ExtentReports();
		extent.createTest("Initialize Setup");
		initializeDriver();
		extent.flush();
	}

	@Test(priority = 1)
	public void navigate_to_homepage_click_on_getstarted() throws Exception {
		
		BrowserStackHomePage bs = launchapplicationandnavigate();
		bs.veryHeader();
		bs.clickOnGetStarted();
		takeSnapShot("Start Page");
	}

	@Test(priority = 2, dataProvider = "userData", retryAnalyzer=RetryAnalyzer.class)
	public void enter_userDetails(HashMap<String,String> input) throws Exception {
		BrowserStackSignUpPage bp = enterDetails();
		bp.veryHeader();
		bp.enterFullName(input.get("fullName"));
		bp.enterBusinessEmail(input.get("email"));
		bp.enterPasswrod(input.get("password"));
		takeSnapShot("SignupPageagain");
	
	}

	
	@AfterTest
	public void teardown() {
		browserteardown();
	}

	@DataProvider(name = "userData")
	public Object[][] userData() {
		
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("fullName", "TestUser1");
		map.put("email", "testuser1@gmail.com");
		map.put("password", "password1");
		//add here
		

		HashMap<String, String> map1= new HashMap<String, String>();
		map1.put("fullName", "TestUser2");
		map1.put("email", "testuser2@gmail.com");
		map1.put("password","password2");
		//add here
		
		
		return new Object[][] { { map },{ map1},
		
		};
	}
}

/*
 * Explanation of Code
 * 
 * Code Line-21 to 27: Setting up browser and website to execute test scripts
 * Code Line-29 to 43: Initializing driver object to BrowserStackHomePage &
 * BrowserStackSignUpPage and performing actions on those pages
 */