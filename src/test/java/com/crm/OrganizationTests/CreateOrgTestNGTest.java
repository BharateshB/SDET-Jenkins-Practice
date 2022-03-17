package com.crm.OrganizationTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibrary.BaseClass;
import com.crm.objectRepository.CreateOrgPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OrgInfoPage;
import com.crm.objectRepository.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateOrgTestNGTest extends BaseClass {
	
	@Test(groups = "smokeSuite")
	public void createOrgTestNGTest() throws Throwable {
		
		String excelOrgName = eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
		
		// Click on organization link
		Homepage hp=new Homepage(driver);
		hp.ClickOnOrgLnk();
		
		// click on create organization link
		OrganizationsPage op=new OrganizationsPage(driver);
		op.createorglink();
		
		// Create organization and save
		CreateOrgPage cop= new CreateOrgPage(driver);
		cop.createOrg(excelOrgName);
		Reporter.log("Organization is created",true);
		
		/*verify the organization*/
		OrgInfoPage oip= new OrgInfoPage(driver);
		String actOrgHeader = oip.OrgNameInfo();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(actOrgHeader.contains(excelOrgName));
		
		
		/*if(actOrgHeader.contains(excelOrgName)) {
			System.out.println(excelOrgName+"---->"+"Organization is verified");
		}
		else {
			System.out.println("organization is not verified");
		}*/
		Reporter.log("organization is verified",true);
	}

}
