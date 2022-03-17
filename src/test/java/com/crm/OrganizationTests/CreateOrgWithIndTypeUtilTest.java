package com.crm.OrganizationTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndTypeUtilTest {
	@Test
	public void createOrgWithIndTypeUtilTest() throws Throwable {
		
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
		String Orgname = eLib.readDataFromExcel("Org", 4, 2)+"_"+jLib.getRandomNumber();
		String IndType = eLib.readDataFromExcel("Org", 4, 3);
		
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
		 
		 //s4:navigate to organizations link
		 driver.findElement(By.linkText("Organizations")).click();
		 
		 //s5: click on create organization link
		 driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		 
		 //s6: enter mandatory fields 
		 driver.findElement(By.name("accountname")).sendKeys(Orgname);
		 
		 //choose “health care” from industry dropdown menu and save
		 WebElement option = driver.findElement(By.name("industry"));
		
		 wLib.select(IndType, option);
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		//s7:logout from application
		 WebElement ele = driver.findElement(By.xpath("//img[@style=\"padding: 0px;padding-left:5px\"]"));
		 
		 wLib.mouseHoverSingleClick(driver, ele);
		 driver.findElement(By.className("drop_down_usersettings")).click();
		 
		 driver.quit();
	}
}
