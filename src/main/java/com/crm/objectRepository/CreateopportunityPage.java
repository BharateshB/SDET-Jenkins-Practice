package com.crm.objectRepository;

import javax.management.loading.PrivateClassLoader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateopportunityPage extends WebDriverUtility {

	@FindBy(name = "potentialname")
	private WebElement oppName;
	
	@FindBy(id = "related_to_type")
	private WebElement relatedToType;
	
	@FindBy(xpath = "//img[@src=\'themes/softed/images/select.gif\']")
	private WebElement locateConLnk;
	
	@FindBy(name = "search_text")
	private WebElement contactName;

	@FindBy(name = "search_text")
	private WebElement CampName;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(name = "leadsource")
	private WebElement leadSrc;
	
	
	@FindBy(xpath = "//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement locateCmpName;

	@FindBy(xpath = "//input[@title=\'Save [Alt+S]\']")
	private WebElement saveBtn;
	
	public CreateopportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOppName() {
		return oppName;
	}

	public WebElement getRelatedToType() {
		return relatedToType;
	}

	public WebElement getLocateConLnk() {
		return locateConLnk;
	}

	public WebElement getContactName() {
		return contactName;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLeadSrc() {
		return leadSrc;
	}

	public WebElement getLocateCmpName() {
		return locateCmpName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//business Library
	public void createopportunity(WebDriver driver, String OPPNAME, String conName, String LeadSource,String CampaignName) {
		oppName.sendKeys(OPPNAME);
		
		select(relatedToType,"Contacts" );
		locateConLnk.click();
		
		switchtowindow(driver, "Contacts");
		contactName.sendKeys(conName);
		
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()=' "+conName+"']")).click();
		
		switchtowindow(driver,"Potentials");
		select(leadSrc, LeadSource);
		
		locateCmpName.click();
		switchtowindow(driver, "Campaigns");
		
		CampName.sendKeys(CampaignName);
		
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+CampaignName+"']")).click();
		
		switchtowindow(driver,"Potentials");
		saveBtn.click();
		
	}
		
	
}
