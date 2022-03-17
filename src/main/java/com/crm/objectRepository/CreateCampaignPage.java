package com.crm.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateCampaignPage extends WebDriverUtility {
	
	/*declaration -use @Findby*/
	@FindBy(name = "campaignname")
	private WebElement campaignName;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement selProdLink;
	
	@FindBy(name = "search_text")
	private WebElement searchText;
	
	@FindBy(name = "search")
	private WebElement searcBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	/*initialization */
	public CreateCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*utilization using getters*/
	public WebElement getCampaignName() {
		return campaignName;
	}


	public WebElement getSelProdLink() {
		return selProdLink;
	}


	public WebElement getSearchText() {
		return searchText;
	}


	public WebElement getSearcBtn() {
		return searcBtn;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	/*business library*/
	/* method to create campaign using campaign name*/
	public void createCampaign(String campName) {
		campaignName.sendKeys(campName);
		saveBtn.click();
	}
	
	
	/* method to create campaign and select product*/
	public void createCampaign(WebDriver driver, String campname, String prodName ) {
		campaignName.sendKeys(campname);
		selProdLink.click();
		switchtowindow(driver, "Products");
		searchText.sendKeys(prodName);
		searcBtn.click();
		driver.findElement(By.xpath("//a[text()='"+prodName+"']")).click();
		switchtowindow(driver, "Campaigns");
		saveBtn.click();
		
	}
}
