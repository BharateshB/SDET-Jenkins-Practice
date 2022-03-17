package com.crm.leadsSearch;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.objectRepository.CreateLeadsPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LeadsInfoPage;
import com.crm.objectRepository.LeadsPage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateLeadsTestNGTest extends BaseClass{

	@Test
	public void createLeadsTestNGTest() throws Throwable {
		
		/* Read Entire Data*/
		//Read data from Excel File
		String ExcelLastName = eLib.readDataFromExcel("Leads", 4, 2);
		String ExcelCompName = eLib.readDataFromExcel("Leads", 4, 3);
		String ExcelLeadsSrc = eLib.readDataFromExcel("Leads", 4, 4);
		String ExcelIndType = eLib.readDataFromExcel("Leads", 4, 5);
		String ExcelAnnRevenue = eLib.readNumDataFromExcel("Leads", 4, 6);
		
		/*click on leads page*/
		Homepage hp=new Homepage(driver);
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
		
		Reporter.log("leads is verified");
		
	}
}
