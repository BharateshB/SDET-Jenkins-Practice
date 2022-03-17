package com.crm.OrganizationTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationPOMTest {
@Test

	public void createorganizationPage() throws Throwable {
	WebDriver driver=null;
	/*Step1:create objects of all utility classes*/
	PropertyFileUtility pLib= new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
	WebDriverUtility wLib=new WebDriverUtility();
	
	
	/*Step2: read all necessary data*/
	String excelOrgName = eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	/* Step3: launch the browser*/
	WebDriverManager.chromedriver().setup();
	WebDriverManager.firefoxdriver().setup();
	
	
	if(BROWSER.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver();
	}
	else if (BROWSER.equalsIgnoreCase("firefox")) {
		driver = new FirefoxDriver();
	}
	else {
		System.out.println("invalid driver");
	}
		
	wLib.maximiseWindow(driver);
	wLib.waitForPageLoad(driver);
	driver.get(URL);
	
	//Step4: launch the application and login
	LoginPage lp= new LoginPage(driver);
	lp.loginToApp(USERNAME, PASSWORD);
	
	//Step5: Click on organization link
	Homepage hp=new Homepage(driver);
	hp.ClickOnOrgLnk();
	
	//step6: click on create organization link
	OrganizationsPage op=new OrganizationsPage(driver);
	op.createorglink();
	
	//step7: Create organization and save
	CreateOrgPage cop= new CreateOrgPage(driver);
	cop.createOrg(excelOrgName);
	
	/*verify the organization*/
	OrgInfoPage oip= new OrgInfoPage(driver);
	String actOrgHeader = oip.OrgNameInfo();
	if(actOrgHeader.contains(excelOrgName)) {
		System.out.println(excelOrgName+"---->"+"Organization is verified");
	}
	else {
		System.out.println("organization is not verified");
	}
	
	
	/*step 8: logout from the app*/
	hp.signoutOfApp(driver);
	driver.quit();
}
}
