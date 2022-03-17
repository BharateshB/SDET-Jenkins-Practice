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
import com.crm.objectRepository.ContactInfoPage;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.CreateOrgPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OrgInfoPage;
import com.crm.objectRepository.OrganizationsPage;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgPOMTest {
@Test
	public void createContactWithOrgPOMTest() throws Throwable {
	
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
	String contactname = eLib.readDataFromExcel("Contact", 4, 2)+"_"+jLib.getRandomNumber();;
	String excelOrgName = eLib.readDataFromExcel("Contact", 4, 3)+"_"+jLib.getRandomNumber();
	
	
	//step2: launch the browser
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
	 
		//Step4: Click on organization link
		Homepage hp=new Homepage(driver);
		hp.ClickOnOrgLnk();
		
		//step5: click on create organization link
		OrganizationsPage op=new OrganizationsPage(driver);
		op.createorglink();
		
		//step6: Create organization and save
		CreateOrgPage cop= new CreateOrgPage(driver);
		cop.createOrg(excelOrgName);
		
		/*step7:verify the organization*/
		OrgInfoPage oip= new OrgInfoPage(driver);
		String actOrgHeader = oip.OrgNameInfo();
		if(actOrgHeader.contains(excelOrgName)) {
			System.out.println(excelOrgName+"---->"+"Organization is verified");
		}
		else {
			System.out.println("organization is not verified");
		}
	 
	 //s8: navigate to contacts
		hp.ClickOnContactsLnk();
		 
		//S5: click on create contact page link
		ContactsPage cp=new ContactsPage(driver);
		cp.createContactlnk();
		
	 //s9:create a new contact and save
		CreateContactPage ccp= new CreateContactPage(driver);
		ccp.createContact(driver, contactname, excelOrgName);
		
		
		/*verify the contact */
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actHeader = cip.contactVerify();
		if (actHeader.contains(contactname)) {
			System.out.println(contactname+"----------------> "+"contact is created");
		}
		 else {
			System.out.println("contact is not created");
		}
	
	 //s6:logout from the application
	 hp.signoutOfApp(driver);
	 
	 driver.quit();
	
}
}
