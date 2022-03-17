package com.crm.CreateCampaignwithProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProdPOMTest {
	@Test
	public void createCampaignWithProdPOMTest() throws Throwable {
		
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		/*Step 1create objects of utility classes*/
		PropertyFileUtility pLib = new PropertyFileUtility();
		JavaUtility jLib = new JavaUtility();
		ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
		WebDriverUtility wLib = new WebDriverUtility();
		
		/*Step 2: read all necessary data*/
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		
		String excelcampaignName = eLib.readDataFromExcel("Campaign", 7, 2)+"_"+jLib.getRandomNumber();
		String excelprodName = eLib.readDataFromExcel("Campaign", 7, 3)+"_"+jLib.getRandomNumber();
		String excelProdCat = eLib.readDataFromExcel("Campaign", 7, 4);
		
		/*Step 3: launch the browser*/
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		
		
		
		/*Step4: launch the application and login*/
		driver.get(URL);
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Create Product
		/*Step5: Click on products link*/
		Homepage hp=new Homepage(driver);
		hp.ClickOnProductsLnk();
		
		/*Step6:Click on create Products link*/
		ProductsPage pp=new ProductsPage(driver);
		pp.createProdLink();
		
		/*Step7:Enter mandatory fields and save */
		CreateProductspPage cpp=new CreateProductspPage(driver);
		cpp.createproduct(excelprodName, excelProdCat);
		
		/*Step8: verify the product*/
		ProdInfoPage pip=new ProdInfoPage(driver);
		pip.productHeader(excelprodName);
		
		// create a campaign with product 
		/*Step9: click on more link*/
		hp.ClickOnMoreLnk();
		
		/*Step10: click on campaign link*/
		hp.ClickOnCampaignLnk();
		
		/*Step11: click on create campaign link*/
		CampaignsPage cp= new CampaignsPage(driver);
		cp.createCampaignlink();
		
		/*Step12:Enter the mandatory fields and select product*/
		CreateCampaignPage ccp= new CreateCampaignPage(driver);
		ccp.createCampaign(driver, excelcampaignName, excelprodName);
		
		/*Step13: verify the campaign */
		CampaignInfoPage cip= new CampaignInfoPage(driver);
		cip.campHeader(excelcampaignName);
		
		/*Step14: sign out of the app*/
		hp.signoutOfApp(driver);
		
		driver.quit();
		
	}		
}
