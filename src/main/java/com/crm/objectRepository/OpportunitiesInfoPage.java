package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesInfoPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement opportunityTitle;
	
	public OpportunitiesInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void oppTitle(String expOppTitle) {
		String Oppinfo = opportunityTitle.getText();
		if (Oppinfo.contains(expOppTitle)) {
			System.out.println(Oppinfo +"---------->"+ "Opportunities is created");
		}
		else {
			System.out.println("opportunities not created ");
		}
	}
}
