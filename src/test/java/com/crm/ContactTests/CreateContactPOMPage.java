package com.crm.ContactTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactPOMPage {
	@Test
	public void createContactPOM() throws Throwable {
		
		/*invoke browser drivers*/
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		/* create objects of utility class*/
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
		WebDriverUtility wLib = new WebDriverUtility();
		
		/*S1: read all necessary data*/
		//From propertyutility file
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");

		//From excelutility file
		String contactname = eLib.readDataFromExcel("Contact", 1, 2);
		
		
		/*S2: Launch the browser*/
		
	 WebDriver driver=null;
	 if (BROWSER.equalsIgnoreCase("chrome")) {
		driver=new ChromeDriver();
	}
	 else if (BROWSER.equalsIgnoreCase("firefox")) {
		driver=new FirefoxDriver();
	}
	 else {
		System.out.println("invalid driver");
	}
	 wLib.maximiseWindow(driver);
	 wLib.waitForPageLoad(driver);
	 driver.get(URL);
	 
	 //s3:login to the application
	 	LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	 
	 //s4: navigate to contacts
		Homepage hp=new Homepage(driver);
		hp.ClickOnContactsLnk();
	 
		//S5: click on create contact page link
		ContactsPage cp=new ContactsPage(driver);
		cp.createContactlnk();
		
	 //s5:create a new contact and save
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(contactname);
	
	 
	 //s6:logout from the application
	 hp.signoutOfApp(driver);
	 
	 driver.quit();
	}

}
