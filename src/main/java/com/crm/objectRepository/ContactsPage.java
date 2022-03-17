package com.crm.objectRepository;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

public class ContactsPage {
		
	 	WebDriver driver;
		@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
		private WebElement createcontactlink;
		
		
		
		
		public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

		public WebElement getCreatecontactlink() {
			return createcontactlink;
		}
		

		//business Library
		public void createContactlnk() {
			createcontactlink.click();
		}
			
}
