package com.crm.OrganizationTests;


import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseClass;

import com.crm.objectRepository.CreateOrgPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.OrgInfoPage;
import com.crm.objectRepository.OrganizationsPage;

@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateorgWithIndTestNGTest extends BaseClass {

	
	@Test(groups = "regressionSuite")
	public void createorgWithIndTestNGTest() throws Throwable {
		
		/*Step1: read all necessary data*/
		String excelOrgName = eLib.readDataFromExcel("Org", 4, 2)+"_"+jLib.getRandomNumber();
		String excelIndtype = eLib.readDataFromExcel("Org", 4, 3);
		
		
		//Step2: Click on organization link
		Homepage hp=new Homepage(driver);
		hp.ClickOnOrgLnk();
		Reporter.log("clicked on organization link");
		
		//step3: click on create organization link
		OrganizationsPage op=new OrganizationsPage(driver);
		op.createorglink();
		Reporter.log("clicked on new organization");
		
		//step4: Create organization and save
		CreateOrgPage cop= new CreateOrgPage(driver);
		cop.createOrg(excelOrgName, excelIndtype);
		Reporter.log("organization is created");
		
		/*verify the organization*/
		OrgInfoPage oip= new OrgInfoPage(driver);
		String actOrgHeader = oip.OrgNameInfo();
		if(actOrgHeader.contains(excelOrgName)) {
			System.out.println(excelOrgName+"---->"+"Organization is verified");
		}
		else {
			System.out.println("organization is not verified");
		}		Reporter.log("organization verified successfully");
		
		
	}
}
