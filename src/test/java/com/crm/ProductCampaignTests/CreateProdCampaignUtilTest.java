package com.crm.ProductCampaignTests;

import java.io.FileInputStream;
import java.nio.channels.NonWritableChannelException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.CampaignInfoPage;
import com.crm.objectRepository.CampaignsPage;
import com.crm.objectRepository.CreateCampaignPage;
import com.crm.objectRepository.CreateProductspPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.ProdInfoPage;
import com.crm.objectRepository.ProductsPage;

public class CreateProdCampaignUtilTest {

	@Test
	public void createProdCampaignUtilTest() throws Throwable {
		//Create objects of utilities
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
		WebDriverUtility wLib =  new WebDriverUtility();
		JavaUtility jLib = new JavaUtility();
		
		
		/*S1: read the entire data*/
		//read from property file
		
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		//Read data from the excel sheet
		
		String campName = eLib.readDataFromExcel("Campaign", 4, 2);
		String campNameRand = campName+"_"+jLib.getRandomNumber();
		
		String prodName =  eLib.readDataFromExcel("Campaign", 4, 3);
		String prodNameRand = prodName+"_"+jLib.getRandomNumber();
		
		//S2: launch the browser
		WebDriver driver=null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			System.out.println("invalid driver");
		}
		//s3:enter the url
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		
		//s4: login to the app
		 LoginPage lp = new LoginPage(driver);
		 lp.loginToApp(USERNAME, PASSWORD);
		 
		 //s5:enter the product and save
		 Homepage hp= new Homepage(driver);
		 hp.ClickOnProductsLnk();
		 Thread.sleep(2000);
		 //click on create prod link
		 ProductsPage pp= new ProductsPage(driver);
		 pp.getCreateProductsLink();
		 
		 CreateProductspPage cpp= new CreateProductspPage(driver);
		 cpp.createProduct(prodNameRand);
		 
		 // s6:verify for product
		ProdInfoPage pip = new ProdInfoPage(driver);
		pip.productHeader(prodNameRand);
		 
		 //s7:create the campaign with more link
		 hp.ClickOnCampaignLnk();
		 
		 //s8:click on campaign link
		 CampaignsPage cp= new CampaignsPage(driver);
		 cp.createCampaignlink();
		
		 //S9:create campaign
		 CreateCampaignPage ccp= new CreateCampaignPage(driver);
		 ccp.createCampaign(driver, campNameRand, prodNameRand);
		 
		 //verify the campaign creation
		 CampaignInfoPage cip= new CampaignInfoPage(driver);
		 cip.campHeader(campNameRand);
		 
		 //s: logout from the app
		hp.signoutOfApp(driver);
			driver.quit();
	}
	
}
