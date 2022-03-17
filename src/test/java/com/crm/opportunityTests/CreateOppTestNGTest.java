package com.crm.opportunityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOppTestNGTest extends BaseClass {

	@Test
	public void createOppTestNGTest() throws Throwable {
		/*S1: read all necessary data*/
		//From excelutility file
		String opportunityName = eLib.readDataFromExcel("opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String contactname = eLib.readDataFromExcel("opportunities", 1, 4)+"_"+jLib.getRandomNumber();;
		String campaignName = eLib.readDataFromExcel("opportunities", 1, 6)+"_"+jLib.getRandomNumber();
		//String relatedTo = eLib.readDataFromExcel("opportunities", 1, 3);
		String leadSource = eLib.readDataFromExcel("opportunities", 1, 5);
		 
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
		 Reporter.log("campaign is verified",true);
		  
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
		Reporter.log("opportunity is verified", true);
	}
}
