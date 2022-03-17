package com.crm.ContactTests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.crm.objectRepository.ContactInfoPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactUtilTest {
	@Test
	public void createContactUtilTest() throws Throwable {

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
		String contactName = eLib.readDataFromExcel("Contact", 1, 2);
		
		
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
	 
	 //s3:login to the application
	 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	 driver.findElement(By.id("submitButton")).click();
	 
	 //s4: navigate to contacts
	 driver.findElement(By.linkText("Contacts")).click();
	 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	 
	 //s5:create a new contact and save
	 driver.findElement(By.name("lastname")).sendKeys(contactName);
	 driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	 
	 //S6: verify
	 ContactInfoPage cip = new ContactInfoPage(driver);
	 String actHeader = cip.contactVerify();
		if (actHeader.contains(contactName)) {
			System.out.println(contactName+"----------------> "+"contact is created");
		}
		 else {
			System.out.println("contact is not created");
		}	 
	 //s6:logout from the application
	 
	 WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	 wLib.mouseHoverSingleClick(driver, ele);
	 driver.findElement(By.className("drop_down_usersettings")).click();
	 
	 driver.quit();

	}
}