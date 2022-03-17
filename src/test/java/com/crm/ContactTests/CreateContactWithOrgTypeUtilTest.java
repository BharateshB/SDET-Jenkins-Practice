package com.crm.ContactTests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrgTypeUtilTest {
@Test
public void createContactWithOrgTypeUtilTest() throws Throwable {
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
	String contactname = eLib.readDataFromExcel("Contact", 4, 2);
	String Orgname = eLib.readDataFromExcel("Contact", 4, 3)+"_"+jLib.getRandomNumber();
	
	
	//step2: launch the browser
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
	 
	 //s3:login to the application
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
	 
	 //s6: navigate to contacts
	 driver.findElement(By.linkText("Contacts")).click();
	 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	 
	 //s7:create a new contact with existing organization and save
	 driver.findElement(By.name("lastname")).sendKeys(contactname);
	 driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
	
	 wLib.switchtowindow(driver, "Accounts");
	 
	 driver.findElement(By.name("search_text")).sendKeys(Orgname);
	 driver.findElement(By.name("search")).click();
	 driver.findElement(By.xpath("//a[text()=\"Orgname\"]")).click();

	 wLib.switchtowindow(driver, "Contacts");
	 
	 driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	 
	 //s6:logout from the application
	 
	 WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	 wLib.mouseHoverSingleClick(driver, ele);
	 driver.findElement(By.className("drop_down_usersettings")).click();
	 
	 driver.quit();
}
}
