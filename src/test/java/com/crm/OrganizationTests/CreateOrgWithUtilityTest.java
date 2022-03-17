package com.crm.OrganizationTests;

import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithUtilityTest {
@Test	
public void createOrgWithUtilityTest() throws Throwable{
	
	/*invoke browser drivers*/
	WebDriverManager.chromedriver().setup();
	WebDriverManager.firefoxdriver().setup();
	
	/* create objects of utility class*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/*S1: read all necessary data*/
	//From propertyutility file
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");

	//From excelutility file
	String Orgname = eLib.readDataFromExcel("Org", 1, 2)+"_"+jLib.getRandomNumber();
	
	/*S2: Launch the browser*/
	
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
	 wLib.maximiseWindow(driver);
	 wLib.waitForPageLoad(driver);
	 driver.get(URL);
		
		//step2 login to the application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step3:navigate to organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4: click on create Organization link
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		//step5: create org with mandatory fields and Save
		driver.findElement(By.name("accountname")).sendKeys(Orgname);
		
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step 6:logout from the application
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		 wLib.mouseHoverSingleClick(driver, ele);
		 driver.findElement(By.className("drop_down_usersettings")).click();
		
		
		driver.quit();

}
}
