package com.crm.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.CreateOrgPage;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OrgInfoPage;
import com.crm.objectRepository.OrganizationsPage;

public class CreateOrgWithIndAndTypeMultipleDataTest {

	//Create Obj for all utilities
	PropertyFileUtility pLib = new PropertyFileUtility();
	ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
	WebDriverUtility wLib = new WebDriverUtility();
	JavaUtility jLib = new JavaUtility();
	
	@Test(dataProvider = "getData")
	public void createOrgWIthIndAndType(String orgName, String indTypes, String type) throws Throwable {

	//read data 
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	
	String randorgname = orgName+jLib.getRandomNumber();
	//launch the application
	WebDriver driver = null;
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
		System.out.println("invalid browser");
	}
	
	wLib.maximiseWindow(driver);
	wLib.waitForPageLoad(driver);
	driver.get(URL);
	
	//login to application
	LoginPage lp = new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	Reporter.log("login successful",true);
	
	//navigate to organization
	Homepage hp = new Homepage(driver);
	hp.ClickOnOrgLnk();
	Reporter.log("navigated to org link",true);
	
	//create Org
	OrganizationsPage op = new OrganizationsPage(driver);
	op.createorglink();
	Reporter.log("click on create org link",true);
	
	//create new org
	CreateOrgPage cop = new CreateOrgPage(driver);
	cop.createOrg(randorgname, indTypes,type);
	Reporter.log("create org with insustry type",true);
	
	//validate
	OrgInfoPage oip = new OrgInfoPage(driver);
	String actOrgHeader = oip.OrgNameInfo();
	if(actOrgHeader.contains(randorgname)) {
		System.out.println(randorgname+"---->"+"Organization is verified");
	}
	else {
		System.out.println("organization is not verified");
	}
	Reporter.log("verification successful",true);		
	
	//logout
	hp.signoutOfApp(driver);
	
	driver.quit();
}
	

@DataProvider()
public Object[][] getData() throws Throwable
{
	Object[][] data = eLib.readmultipleDataFromExcel("OrgDPTestOIT",0);
	return data;
}


}
