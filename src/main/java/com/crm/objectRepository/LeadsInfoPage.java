package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsInfoPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement leadsHeadereElement;
	
	public LeadsInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLeadsHeadereElement() {
		return leadsHeadereElement;
	}
	
	public String LeadsInfo() {
		String actHeader = leadsHeadereElement.getText();
		return actHeader;
	}
	
}
