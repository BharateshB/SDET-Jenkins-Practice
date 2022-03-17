package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProdInfoPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement prodHeader;
	
	public ProdInfoPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}


	public WebElement getProdHeader() {
		return prodHeader;
	}
	
	public void productHeader(String prodName) {
		String ProdTitle = prodHeader.getText();
		if (ProdTitle.contains(prodName)) {
			System.out.println(ProdTitle+" " +"product successfully created");
		}
		else {
			System.out.println(ProdTitle+""+"product not created");
		}
	
	}
	
}