package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {

	/*declaration*/
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOppLink;
	
	
	/* initialization*/
	public OpportunitiesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*utilization*/
	public WebElement getCreateOppLink() {
		return createOppLink;
	}
	
	/*business library*/
	/*method to click on create opportunity link*/
	public void createOppLnk() {
		createOppLink.click();
	}
	
}
