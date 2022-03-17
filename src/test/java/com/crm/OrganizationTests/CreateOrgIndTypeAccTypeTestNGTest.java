package com.crm.OrganizationTests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;
import com.crm.objectRepository.CreateOrgPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OrgInfoPage;
import com.crm.objectRepository.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgIndTypeAccTypeTestNGTest extends BaseClass {

	@Test(groups = "regressionSuite")
	public void createOrgIndTypeAccTypeTestNGTest() throws Throwable {
		/*Step2: read all necessary data*/
		String excelOrgName = eLib.readDataFromExcel("Org", 7, 2)+"_"+jLib.getRandomNumber();
		String excelIndtype = eLib.readDataFromExcel("Org", 7, 3);
		String excelOrgtype = eLib.readDataFromExcel("Org", 7, 4);
		
		//Step5: Click on organization link
		Homepage hp=new Homepage(driver);
		hp.ClickOnOrgLnk();
		Reporter.log("contact is created");
		
		//step6: click on create organization link
		OrganizationsPage op=new OrganizationsPage(driver);
		op.createorglink();
		
		
		//step7: Create organization and save
		CreateOrgPage cop= new CreateOrgPage(driver);
		cop.createOrg(excelOrgName, excelIndtype,excelOrgtype);
		Reporter.log("organization is created");
		
		/*verify the organization*/
		OrgInfoPage oip= new OrgInfoPage(driver);
		String actOrgHeader = oip.OrgNameInfo();
		if(actOrgHeader.contains(excelOrgName)) {
			System.out.println(excelOrgName+"---->"+"Organization is verified");
		}
		else {
			System.out.println("organization is not verified");
		}
		
	}
}
