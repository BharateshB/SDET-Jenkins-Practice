package com.crm.objectRepository;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateOrgPage extends WebDriverUtility{

	
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement selindType;
	
	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement selType;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	public CreateOrgPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	/*create organization with name only*/
	public void createOrg(String orgname) {
		OrgNameEdt.sendKeys(orgname);
		savebtn.click();
	}
	
	/*create organization with industry type*/
	public void createOrg(String orgname, String indType) {
		OrgNameEdt.sendKeys(orgname);
		select(selindType, indType);
		savebtn.click();
	}
	
	/*create organization with industry type and orgtype*/
	public void createOrg(String orgname, String indType, String orgType) {
		OrgNameEdt.sendKeys(orgname);
		select(selindType, indType);
		select(selType, orgType);
		savebtn.click();
	}
}
