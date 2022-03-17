package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	/*Step1: declaration -use @FindBy annotation*/
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement conheader;
	
	/*Step2: Initiaization - use constructor*/
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*Step3: Utilization - provide getters*/
	public WebElement getConheader() {
		return conheader;
	}
	
	/*Business Library*/
	/* Verify contact*/
	public String contactVerify() {
		 String Actheader = conheader.getText();
		 return Actheader;
		 /*
		 if (header.contains(contactname)) {
				System.out.println(contactname+"----------------> "+"contact is created");
			}
			 else {
				System.out.println("contact is not created");
			}
	}*/
}
}