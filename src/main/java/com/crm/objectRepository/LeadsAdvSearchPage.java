package com.crm.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class LeadsAdvSearchPage extends WebDriverUtility {

	@FindBy(id = "fcol0")
	private WebElement salutationElement;
	
	@FindBy(id = "fop0")
	private WebElement salutationCompareElement;
	
	@FindBy(id = "fval0")
	private WebElement salutationValueElement;
	
	@FindBy(xpath = "(//input[@class='crmbutton small create'])[2]")
	private WebElement searchBtnElement;
	
	public WebElement getSearchBtnElement() {
		return searchBtnElement;
	}

	public LeadsAdvSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSalutationElement() {
		return salutationElement;
	}

	public WebElement getSalutationCompareElement() {
		return salutationCompareElement;
	}

	public WebElement getSalutationValueElement() {
		return salutationValueElement;
	}
	
	public void leadsSearch(String salutation, String SalutComp,String SalutValue) {
		select(salutation,salutationElement );
		select(SalutComp, salutationCompareElement);
		salutationValueElement.sendKeys(SalutValue);
		searchBtnElement.click();
		
	}
	
	public void verifyleads(WebDriver driver,String lastName) {
		 String LeadsLastName = driver.findElement(By.xpath("//a[text()='"+lastName+"']")).getText();
			if (LeadsLastName.equalsIgnoreCase(lastName)) {
				System.out.println("valid Leads");
			}
			else {
				System.out.println("invalid");
			}
			
	}
	
	
}
