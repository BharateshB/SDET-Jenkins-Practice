package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateLeadsPage extends WebDriverUtility{

	@FindBy(name = "lastname")
	private WebElement LastNameElement;
	
	@FindBy(name = "company")
	private WebElement companyNameElement;
	
	@FindBy(name = "leadsource")
	private WebElement leadsSrcDropdownElement;
	
	@FindBy(name = "industry")
	private WebElement industryTypeElement;
	
	@FindBy(name = "annualrevenue")
	private WebElement annualRevenElement;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtnElement;
	
	public CreateLeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastName() {
		return LastNameElement;
	}

	public WebElement getCompanyName() {
		return companyNameElement;
	}

	public WebElement getLeadsSrcDropdown() {
		return leadsSrcDropdownElement;
	}

	public WebElement getIndustryType() {
		return industryTypeElement;
	}

	public WebElement getAnnualRevenElement() {
		return annualRevenElement;
	}

	public WebElement getSaveBtnElement() {
		return saveBtnElement;
	}
	
	//business library
	public void createLeads(String Lastname, String CompName) {
		LastNameElement.sendKeys(Lastname);
		companyNameElement.sendKeys(CompName);
		saveBtnElement.click();
	}
	
	public void createLeads(String LastName, String CompName, String leadSrc, String IndType, String AnnualRevenue) {
		LastNameElement.sendKeys(LastName);
		companyNameElement.sendKeys(CompName);
		select(leadsSrcDropdownElement, leadSrc);
		select(industryTypeElement, IndType);
		annualRevenElement.sendKeys(AnnualRevenue);
		saveBtnElement.click();
		
	}
	
	
	
}
