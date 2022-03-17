package com.crm.PRACTICE;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.objectRepository.Homepage;

public class ContactsLastNamePrintTest extends BaseClass{

	@Test
	public void contactsLastNamePrintTest() {
		
		Homepage hp = new Homepage(driver);
		hp.ClickOnContactsLnk();
		
		
		List<WebElement> elements = driver.findElements(By.xpath("//table/tbody/tr[*]/td[4]/a[@title='Contacts']"));
		int count = elements.size();
		
		
		// print all contacts
		/*
			for(int i=1;i<=count;i++) {
			String lastname = driver.findElement(By.xpath("(//table/tbody/tr[*]/td[4]/a[@title='Contacts'])["+i+"]")).getText();
			System.out.println(lastname);
				}
				*/
		
		
	}
}
