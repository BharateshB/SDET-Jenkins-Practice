package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	
	/*declaration -use @Findby*/
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createCampainlnk;
	
	/*initialization */
	public CampaignsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/*utilization using getters*/
	public WebElement getCreateCampainlnk() {
		return createCampainlnk;
	}
	
	/*business library*/
	/*method to create campaign link*/
	public void createCampaignlink() {
		createCampainlnk.click();
	}
}
