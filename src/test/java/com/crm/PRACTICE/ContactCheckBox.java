package com.crm.PRACTICE;

import java.awt.Checkbox;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.Homepage;

public class ContactCheckBox extends BaseClass {

	@Test
	
	public void contactCheckBox() throws Throwable {
		
		Homepage hp= new Homepage(driver);
		hp.ClickOnContactsLnk();
		
		wLib.scrollAction(driver);
		Thread.sleep(2000);
		
		List<WebElement> checkbox = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td/input"));
		int count = checkbox.size();
		System.out.println(count);
		
		
		//check all contact boxes
		/*
		for(int i=2;i<=count;i++) {
		driver.findElement(By.xpath("(//table[@class='lvt small']/tbody/tr/td/input)["+i+"]")).click();
		Thread.sleep(1000);
		}
		*/
		
		
		//check 5th contact and delete it
		/*
		int check=0;
		for(int i=2;i<=count;i++) {
			check++;
			if(check==5)
			{	
			driver.findElement(By.xpath("(//table[@class='lvt small']/tbody/tr/td/input)["+check+"]")).click();
			driver.findElement(By.xpath("(//a[text()='del'])["+check+"]")).click();
			wLib.acceptalert(driver);
			Thread.sleep(3000);
			break;
			}
			}
			*/
		
		//click on last contact
		
		driver.findElement(By.xpath("(//table[@class='lvt small']/tbody/tr/td/input)["+count+"]")).click();
		
		
		
		Thread.sleep(5000);
	}
}
