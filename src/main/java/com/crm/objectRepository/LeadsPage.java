package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {
	
	//Declaration
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createLeadsLnk;
	
	@FindBy(linkText = "Go to Advanced Search")
	private WebElement advSrchElement;
	
	//initialization
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//getters
	public WebElement getCreateLeadsLnk() {
		return createLeadsLnk;
	}
	
	public WebElement getAdvSrchElement() {
		return advSrchElement;
	}

	//business Library
	public void leadslnk() {
		createLeadsLnk.click();
	}
	
	public void advSrchLnk() {
		advSrchElement.click();
	}
	
}
