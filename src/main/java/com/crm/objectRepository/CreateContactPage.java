package com.crm.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

public class CreateContactPage extends WebDriverUtility {
	
	/*Step1: declaration -use @FindBy annotation*/
	@FindBy(name = "lastname")
	private WebElement LastName;
	
	@FindBy(xpath = "//img[@title='Select']")
	private WebElement selectOrgImg;                                               
	
	@FindBy(name = "search_text")
	private WebElement searchOrgName;
	
	@FindBy(name = "search")
	private WebElement searchbtn;
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//select[@name='leadsource']")
	private WebElement leadsSource;
	
	/*Step2: Initiaization - use constructor*/
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	/*Step3: Utilization - provide getters*/
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getLastName() {
		return LastName;
	}
	
	
	public WebElement getSelectOrgImg() {
		return selectOrgImg;
	}

	//business library
	/*create contact with last name*/
	public void createContact(String lastname) {
		LastName.sendKeys(lastname);
		saveBtn.click();
	}
	
	/*create contact with last name and organization type*/
	public void createContact(WebDriver driver, String lastname, String orgName) {
		LastName.sendKeys(lastname);
		selectOrgImg.click();
		switchtowindow(driver, "Accounts");
		searchOrgName.sendKeys(orgName);
		searchbtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchtowindow(driver, "Contacts");
		saveBtn.click();		
	}
	
	/*create contact with last name and organization type and Leads Source*/
	public void createContact(WebDriver driver, String lastname, String orgName, String LeadsSrc) {
		LastName.sendKeys(lastname);
		selectOrgImg.click();
		switchtowindow(driver, "Accounts");
		searchOrgName.sendKeys(orgName);
		searchbtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		switchtowindow(driver, "Contacts");
		select(leadsSource, LeadsSrc);
		saveBtn.click();
	}
	
}
