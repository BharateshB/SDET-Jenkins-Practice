package com.crm.leadsSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.CreateLeadsPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LeadsAdvSearchPage;
import com.crm.objectRepository.LeadsInfoPage;
import com.crm.objectRepository.LeadsPage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeadsSearchLeadsNoPOMTest extends WebDriverUtility {

	@Test
	
	public void leadsSearchLeadsNoPOMTest() throws Throwable {
		
		/* Read Entire Data*/
		//Read data From Properties file
		
		PropertyFileUtility pf = new PropertyFileUtility();
		String BROWSER = pf.readDataFromPropertyFile("browser");
		String URL = pf.readDataFromPropertyFile("url");
		String USERNAME = pf.readDataFromPropertyFile("username");
		String PASSWORD = pf.readDataFromPropertyFile("password");
		
		//Read data from Excel File
		
		
		ExcelFileUtilityJava ef = new ExcelFileUtilityJava();
		String ExcelSalutation = ef.readDataFromExcel("leadsSearchLeadNo", 1, 1);
		String ExcelSalutCompare = ef.readDataFromExcel("leadsSearchLeadNo", 1, 2);
		String ExcelSalutValue = ef.readDataFromExcel("leadsSearchLeadNo", 1, 3);
		
		String ExcelLastName = ef.readDataFromExcel("Leads", 4, 2);
		String ExcelCompName = ef.readDataFromExcel("Leads", 4, 3);
		String ExcelLeadsSrc = ef.readDataFromExcel("Leads", 4, 4);
		String ExcelIndType = ef.readDataFromExcel("Leads", 4, 5);
		String ExcelAnnRevenue = ef.readNumDataFromExcel("Leads", 4, 6);
		
		
		/*Launch the Browser*/ 
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver =null;
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver =new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("invalid driver");
		}
		
		maximiseWindow(driver);
		waitForPageLoad(driver);
		
		/*enter URL*/
		driver.get(URL);
		
		/*Login to the application*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/*click on leads page*/
		Homepage hp = new Homepage(driver);
		hp.clickOnLeadsLnk();
		
		//create leads
		/* click on create leads link*/
		LeadsPage lsp= new LeadsPage(driver);
		lsp.leadslnk();
		
		/*create leads page*/
		CreateLeadsPage clp= new CreateLeadsPage(driver);
		clp.createLeads(ExcelLastName, ExcelCompName, ExcelLeadsSrc, ExcelIndType, ExcelAnnRevenue);
		
		/*verify lead*/
		LeadsInfoPage lip= new LeadsInfoPage(driver);
		String actHeader=lip.LeadsInfo();
		Assert.assertTrue(actHeader.contains(ExcelLastName));
		
		/*if (actHeader.contains(ExcelLastName)) {
			System.out.println(ExcelLastName+"--------->"+"Lead is successfully created");
		}
		else {
			System.out.println("lead is not created");
		}*/
		
		hp.clickOnLeadsLnk();
		lsp.advSrchLnk();
		
		LeadsAdvSearchPage lasp= new LeadsAdvSearchPage(driver);
		lasp.leadsSearch(ExcelSalutation, ExcelSalutCompare, ExcelSalutValue);
		
		/*sign out*/
		hp.signoutOfApp(driver);
		driver.quit();
		
	}
}
