package com.crm.ContactTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
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
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateConWithOrgTestNGTest extends BaseClass {

	@Test(groups = "regressionSuite")
	public void createConWithOrgTestNGTest() throws Throwable {

		//From excelutility file
		String contactname = eLib.readDataFromExcel("Contact", 4, 2)+"_"+jLib.getRandomNumber();;
		String excelOrgName = eLib.readDataFromExcel("Contact", 4, 3)+"_"+jLib.getRandomNumber();
		 
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
			Reporter.log("organzation is verified");
			
		 
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
			Assert.assertTrue(actHeader.contains(contactname));
			/*if (actHeader.contains(contactname)) {
				System.out.println(contactname+"----------------> "+"contact is created");
			}
			 else {
				System.out.println("contact is not created");
			}*/
			Reporter.log("contact is verified");
		 
	}
}
