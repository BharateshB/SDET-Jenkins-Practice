package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bsh.This;

public class CampaignInfoPage {

	/*declaration -use @Findby*/
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement campTitle;
	
	/*initialization */
	public CampaignInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*utilization using getters*/
	public WebElement getCampTitle() {
		return campTitle;
	}

	/*business library*/
	public void campHeader(String campName) {
		String CampaignHeader = campTitle.getText();
		if (CampaignHeader.contains(campName)) {
			System.out.println(campName+"------>"+"campaign is created successfully");
		}
		else {
			System.out.println("campaign not created");
		}
	}
}
