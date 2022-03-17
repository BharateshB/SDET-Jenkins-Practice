package com.crm.ContactTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

public class CreateContactWithOrgTest {
@Test
public void createContactWithOrgTest() throws Throwable {
	/** Step1: Get all the data**/
	//Get the data from property file
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
	 Sheet sh = wb.getSheet("Contact");
	 Row ro = sh.getRow(4);
	 Cell ce = ro.getCell(2);
	 String LastName = ce.getStringCellValue();
	 
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
	 
	 //s4: navigate to contacts
	 driver.findElement(By.linkText("Contacts")).click();
	 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	 
	 //s5:create a new contact with existing organization and save
	 driver.findElement(By.name("lastname")).sendKeys(LastName);
	 driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
	 ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
	 driver.switchTo().window(newTb.get(1));
	 driver.findElement(By.id("4")).click();
	 driver.switchTo().window(newTb.get(0));
	 driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	 
	 //s6:logout from the application
	 
	 WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	 Actions act= new Actions(driver);
	 act.moveToElement(ele).click().perform();
	 
	 driver.findElement(By.className("drop_down_usersettings")).click();
	 
	 driver.quit();
	
}
}
