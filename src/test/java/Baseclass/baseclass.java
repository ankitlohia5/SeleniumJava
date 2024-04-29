package Baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;

import demo.POM.BrowserStackHomePage;
import demo.POM.BrowserStackSignUpPage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class baseclass {
	WebDriver driver;
	BrowserStackHomePage objBrowserStackHomePage;
	Properties prop;
	BrowserStackSignUpPage objBrowserStackSignUpPage;
	ExtentReports extent;

	public void initializeDriver() throws IOException {

		// properties
		prop = new Properties();
		FileInputStream fs = new FileInputStream(
				"C:\\Users\\ankitl\\eclipse-workspace\\POM\\src\\test\\java\\Utility\\globalproperties.properties");
		prop.load(fs);
		String browsername = prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("Chrome")) {
			// Chromedriver
			driver = new ChromeDriver();

		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public BrowserStackHomePage launchapplicationandnavigate() {

		driver.get(prop.getProperty("url"));
		objBrowserStackHomePage = new BrowserStackHomePage(driver);

		return objBrowserStackHomePage;
	}

	public BrowserStackSignUpPage enterDetails() {
		objBrowserStackSignUpPage = new BrowserStackSignUpPage(driver);

		return objBrowserStackSignUpPage;
	}

	public void takeSnapShot(String testcasename) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(SrcFile, DestFile);
	}

	public ExtentReports ExtentReports() {

		// ExtentReports // ExtentSparkReporter
		String Path = System.getProperty("user.dir") + "\\reports\\testresults.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "Ankit");
		return extent;
	}
	
	public void browserteardown() {
		driver.close();
	}
}
