package com.crm.opportunityTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
//import org.openqa.selenium.interactions.ClickAction;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.CampaignInfoPage;
import com.crm.objectRepository.CampaignsPage;
import com.crm.objectRepository.ContactInfoPage;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateCampaignPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.CreateopportunityPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OpportunitiesInfoPage;
import com.crm.objectRepository.OpportunitiesPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunityPOMTest {

	private static final WebDriver WebDriver = null;

	@Test
	
	public void createOpportunityPOMTest() throws Throwable {
		
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
		String opportunityName = eLib.readDataFromExcel("opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String contactname = eLib.readDataFromExcel("opportunities", 1, 4)+"_"+jLib.getRandomNumber();;
		String campaignName = eLib.readDataFromExcel("opportunities", 1, 6)+"_"+jLib.getRandomNumber();
		//String relatedTo = eLib.readDataFromExcel("opportunities", 1, 3);
		String leadSource = eLib.readDataFromExcel("opportunities", 1, 5);
	
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
		 
		 /*s3:login to the application*/
		 LoginPage lp= new LoginPage(driver);
		 lp.getUsernameEdt().sendKeys(USERNAME);
		 lp.getPasswordEdt().sendKeys(PASSWORD);;
		 lp.getSubmitBtn().click();
		 
		 /*Create Contact*/
		 Homepage hp= new Homepage(driver);
		 hp.ClickOnContactsLnk();
		 
		 ContactsPage cp= new ContactsPage(driver);
		 cp.createContactlnk();
		 
		 CreateContactPage ccp=new CreateContactPage(driver);
		 ccp.createContact(contactname);
		 
		 /*verify contact*/
		 ContactInfoPage cip= new ContactInfoPage(driver);
		 String actHeader = cip.contactVerify();
			if (actHeader.contains(contactname)) {
				System.out.println(contactname+"----------------> "+"contact is created");
			}
			 else {
				System.out.println("contact is not created");
			}
			Reporter.log("contact is verified");

		 
		 /*Create campaign*/
		 hp.ClickOnMoreLnk();
		 
		 hp.ClickOnCampaignLnk();
		 
		 CampaignsPage cmp =new CampaignsPage(driver);
		 cmp.createCampaignlink();
		 
		 CreateCampaignPage ccmp = new CreateCampaignPage(driver);
		 ccmp.createCampaign(campaignName);
		 
		 /*verify campaign*/
		  CampaignInfoPage caip = new CampaignInfoPage(driver);
		  caip.campHeader(campaignName);
		 
		 //Create opportunity
		 /*S4:Click on opportunities link*/
		 hp.ClickOnOpportunitiesLnk();
		 
		 
		 /*S5:Click on Create opportunities link*/
		 OpportunitiesPage op=new OpportunitiesPage(driver);
		 op.createOppLnk();
		 
		 /*create opportunity with related fields*/
		 CreateopportunityPage cop= new CreateopportunityPage(driver);
		 cop.createopportunity(driver, opportunityName, contactname, leadSource, campaignName);	
		 
		 /*verify opportunity*/
		 OpportunitiesInfoPage oip = new OpportunitiesInfoPage(driver);
		 oip.oppTitle(opportunityName);
		 
		 
		 /* */
		 /*Step14: sign out of the app*/
			hp.signoutOfApp(driver);
			
			driver.quit();
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	}
}
