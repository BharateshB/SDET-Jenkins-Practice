package com.crm.PRACTICE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ListenerImplementationClass;
import com.crm.objectRepository.ContactInfoPage;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateContactPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateContactTestNGTest extends BaseClass{

	@Test(retryAnalyzer = com.crm.GenericLibrary.RetryAnalyzerImplementation.class)
	public void createContactTestNGTest() throws Throwable {

		//From excelutility file
		String contactname = eLib.readDataFromExcel("Contact", 1, 2);
	 
	 //s4: navigate to contacts
		Homepage hp=new Homepage(driver);
		hp.ClickOnContactsLnk();
	 
		//S5: click on create contact page link
		ContactsPage cp=new ContactsPage(driver);
		cp.createContactlnk();
		
		//Assert.fail();
		
	 //s5:create a new contact and save
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(contactname);
		
		ContactInfoPage cip= new ContactInfoPage(driver);
		String actHeader = cip.contactVerify();
		Assert.assertTrue(actHeader.contains(contactname));
		
		/*
		if (actHeader.contains(contactname)) {
			System.out.println(contactname+"----------------> "+"contact is created");
		}
		 else {
			System.out.println("contact is not created");
		}
		Reporter.log("contact is verified",true);
		*/
	}
}
