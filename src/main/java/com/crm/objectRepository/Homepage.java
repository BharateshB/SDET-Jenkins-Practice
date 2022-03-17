package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

public class Homepage extends WebDriverUtility{

	////Step1: declaration -use @FindBy annotation
	@FindBy(linkText = "Leads")
	private WebElement LeadsLink;
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationLnk;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactslnk;
	
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitieslnk;
	
	@FindBy(linkText = "Products")
	private WebElement productsLnk;
	
	@FindBy(linkText = "More")
	private WebElement moreLnk;
	
	@FindBy(name = "Campaigns")
	private WebElement campaignsLnk;
	
	
	
	@FindBy(xpath = "//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement administratorimg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutLnk;
	
	//Step2: Initiaization - use constructor
	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Step3: Utilization - provide getters
	
	public WebElement getOrganizationLnk() {
		return organizationLnk;
	}

	public WebElement getContactslnk() {
		return contactslnk;
	}

	public WebElement getOpportunitieslnk() {
		return opportunitieslnk;
	}

	public WebElement getProductsLnk() {
		return productsLnk;
	}

	public WebElement getMoreLnk() {
		return moreLnk;
	}

	public WebElement getCampaignsLnk() {
		return campaignsLnk;
	}

	public WebElement getAdministratorimg() {
		return administratorimg;
	}

	public WebElement getSignoutLnk() {
		return signoutLnk;
	}
	
	public WebElement getLeadsLink() {
		return LeadsLink;
	}

	//business Library
	public void ClickOnOrgLnk() {
		organizationLnk.click();
	}
	
	public void ClickOnContactsLnk() {
		contactslnk.click();
	}
	
	public void ClickOnProductsLnk() {
		productsLnk.click();
	}
	
	
	public void ClickOnOpportunitiesLnk() {
		opportunitieslnk.click();
	}
	
	public void ClickOnMoreLnk() {
		moreLnk.click();
	}
	
	public void ClickOnCampaignLnk() {
		campaignsLnk.click();
	}
	
	public void clickOnLeadsLnk() {
		LeadsLink.click();
	}
	
	
	public void signoutOfApp(WebDriver driver) {
		mouseHoverSingleClick(driver, administratorimg);
		signoutLnk.click();
	}
}
