package com.crm.ContactTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.ContactInfoPage;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.mysql.cj.jdbc.Driver;

public class CreateContactMultipleDataTest {

	/*create objects of utility classes*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtilityJava eLib=new ExcelFileUtilityJava();
	WebDriverUtility wLib= new WebDriverUtility();
	WebDriver driver = null;
	@Test(dataProvider = "getData")
	public void createContactMultipleDataTest(String contactName) throws Throwable {
		
		/* read data from repository*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		
		/*Launch the browser*/
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Invalid driver");
		}
		
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		Reporter.log("launched the browser",true);
		
		driver.get(URL);
		Reporter.log("loaded the url",true);
		
		
		/*login to the app */
		LoginPage lp =new LoginPage(driver);
		lp.getUsernameEdt().sendKeys(USERNAME);
		lp.getPasswordEdt().sendKeys(PASSWORD);
		lp.getSubmitBtn().click();
		Reporter.log("logged in to app",true);
		
		/*click on contacts link*/
		Homepage hp =new Homepage(driver);
		hp.ClickOnContactsLnk();
		Reporter.log("clicked on contacts link",true);
		
		
		/*click on create contacts link */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreatecontactlink().click();
		Reporter.log("clicked on create contacts link ",true);
		
		/*create contact*/
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.createContact(contactName);
		Reporter.log("contact is created",true);
		
		/*verify contact*/
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actHeader = cip.contactVerify();
		if (actHeader.contains(contactName)) {
			System.out.println(contactName+"----------------> "+"contact is created");
		}
		 else {
			System.out.println("contact is not created");
		}
		Reporter.log("contact is verified",true);
		
		/*sign out of app*/
		hp.signoutOfApp(driver);
		Reporter.log("logged out",true);
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData() throws Throwable {
		Object[][] data=eLib.readmultipleDataFromExcel("ContactProviderTest", 0);
		return data;
	}
	
	
}
