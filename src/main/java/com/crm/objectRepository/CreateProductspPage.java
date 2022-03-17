package com.crm.objectRepository;

import java.util.Locale.Category;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebDriverUtility;

public class CreateProductspPage extends WebDriverUtility{
	@FindBy(name = "productname")
	private WebElement ProdName;
	
	@FindBy(name = "productcategory")
	private WebElement chooseProdCat;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	
	public CreateProductspPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}


	public WebElement getProdName() {
		return ProdName;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createProduct(String productname) {
		ProdName.sendKeys(productname);
		saveBtn.click();
	}
	
	public void createproduct(String productname, String Category) {
		ProdName.sendKeys(productname);
		select(chooseProdCat, Category);
		saveBtn.click();
	}
}
