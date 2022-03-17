package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//import okhttp3.internal.http2.Header;

public class OrgInfoPage {

	@FindBy(xpath = "//span[@class=\"dvHeaderText\"]")
	private WebElement orgHeaderText;
	
	public OrgInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getorgHeaderText() {
		return orgHeaderText;
	}
	
	public String OrgNameInfo() {
		String orgInfo = orgHeaderText.getText();
		return orgInfo;
		}
	}
