package com.crm.OrganizationTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateOrgWithIndDDTest {
	@Test
	public void createOrgWithIndDDTest() throws Throwable {
		/** Step1: Get all the data**/
		//Get the data from property file
		Random ran= new Random();
		int rand = ran.nextInt(500);
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		//get the data from excel sheet
		FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\vtigerData.xlsx");
		Workbook wb = WorkbookFactory.create(fi);
		 Sheet sh = wb.getSheet("Org");
		 Row ro = sh.getRow(4);
		 Cell ce = ro.getCell(2);
		 Cell ce2 = ro.getCell(3);
		 String orgName = ce.getStringCellValue();
		 String IndType = ce2.getStringCellValue();
		 
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
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
		 driver.findElement(By.name("accountname")).sendKeys(orgName+" "+rand);
		 
		 //choose “health care” from industry dropdown menu and save
		 WebElement option = driver.findElement(By.name("industry"));
		 Select s=new Select(option);
		 s.selectByValue(IndType);
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		//s7:logout from application
		 WebElement ele = driver.findElement(By.xpath("//img[@style=\"padding: 0px;padding-left:5px\"]"));
		 Actions act= new Actions(driver);
		 act.moveToElement(ele).click().perform();
		 
		 driver.findElement(By.className("drop_down_usersettings")).click();
		 
		 driver.quit();
	}

}
