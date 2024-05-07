package ExcelDataDriven;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.ReadExcelFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcelExample {
	
	WebDriver driver;
@Test(dataProvider="testdata")
public void demoClass(String username, String password) throws InterruptedException {
 driver = new ChromeDriver();
driver.get("https://www.browserstack.com/users/sign_in");
driver.findElement(By.name("user[login]")).sendKeys(username);
driver.findElement(By.name("user[password]")).sendKeys(password);
driver.findElement(By.name("commit")).click();
Thread.sleep(5000);
System.out.println(driver.getTitle()); // Dashboard
Assert.assertTrue(driver.getTitle().matches("Dashboard"), "Invalid credentials");
System.out.println("Login successful");
}

	@AfterMethod
	void ProgramTermination() {
		driver.quit();
	}

	@DataProvider(name = "testdata")
	public Object[][] testDataExample() {
	    ReadExcelFile configuration = new ReadExcelFile("C:\\Users\\ankitl\\eclipse-workspace\\POM\\data.xlsx");
	    int rows = configuration.getRowCount(0);
	    Object[][] signin_credentials = new Object[rows][2];

	    for (int i = 0; i < rows; i++) {
	        signin_credentials[i][0] = configuration.getData(0, i, 0); //00,10  //Username
	        signin_credentials[i][1] = configuration.getData(0, i, 1); //01, 11  //password
	    }
	    return signin_credentials;
	}

	}


/* @DataProvider(name = "testdata"): This line specifies that this method will act as a data provider named "testdata".
 * 
 *  TestNG identifies this method as a source of data for test methods annotated with @Test.
 *  
ReadExcelFile configuration = new ReadExcelFile("C:\\Users\\ankitl\\Downloads\\data.xls");:
 
Here, an instance of the ReadExcelFile class is created with the path to the Excel file 
specified ("C:\Users\ankitl\Downloads\data.xls"). This class is responsible for reading data from the Excel file.

int rows = configuration.getRowCount(0);: This line retrieves the total number of rows 
present in the Excel sheet at index 0 (the first sheet) using the getRowCount() method 
from the ReadExcelFile class.

Object[][] signin_credentials = new Object[rows][2];: 
An object array signin_credentials 
is created to store the test data. The array has a size of rows by 2, where each row 
represents a set of credentials (username and password).

for (int i = 0; i < rows; i++) { ... }: This loop iterates over each row in the Excel sheet.

signin_credentials[i][0] = configuration.getData(0, i, 0);: Inside the loop, getData() 
method from the ReadExcelFile class is used to fetch the data from the Excel sheet at row 
i and column 0 (username) and store it in the first column ([i][0]) of signin_credentials.

signin_credentials[i][1] = configuration.getData(0, i, 1);: Similarly, the password data 
from column 1 of the Excel sheet is stored in the second column ([i][1]) of signin_credentials.

Finally, the signin_credentials array containing the test data (username-password pairs) is returned.

This method essentially reads data from an Excel file and provides it as test data to be 
used in test methods annotated with @Test. 

Each row of the Excel sheet represents a set of 
credentials, and this data is then used to perform login tests in the test method annotated with @Test
*/
