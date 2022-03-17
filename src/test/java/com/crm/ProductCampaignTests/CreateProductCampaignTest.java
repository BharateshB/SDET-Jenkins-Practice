package com.crm.ProductCampaignTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.mysql.cj.callback.UsernameCallback;
@Listeners(com.crm.GenericLibrary.ListenerImplementationClass.class)
public class CreateProductCampaignTest {
@Test

public void createProductCampaignTest() throws Throwable {
	
	Random ran= new Random();
	int rand = ran.nextInt(500);
	/*S1: read the entire data*/
	//read from property file
	
	FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	Properties pobj=new Properties();
	pobj.load(fis);
	String BROWSER = pobj.getProperty("browser");
	String URL = pobj.getProperty("url");
	String USERNAME = pobj.getProperty("username");
	String PASSWORD = pobj.getProperty("password");
	
	//Read data from the excel sheet
	FileInputStream fi= new FileInputStream(".\\src\\test\\resources\\product_campaign_scenario.xlsx");
	Workbook wb = WorkbookFactory.create(fi);
	Sheet sh = wb.getSheet("Product");
	Row ro = sh.getRow(1);
	Cell cel = ro.getCell(2);
	String prodName = cel.getStringCellValue();
	String prodNamerand=prodName+" "+rand;
	Sheet sh2 = wb.getSheet("Campaign");
	Row ro2 = sh2.getRow(1);
	Cell ce2 = ro2.getCell(2);
	String campName = ce2.getStringCellValue();
	String campNameRand = campName+" "+rand;
	//S2: launch the browser
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
	//s3:enter the url
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get(URL);
	
	//s4: login to the app
	 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	 driver.findElement(By.id("submitButton")).click();
	 
	 //s5:enter the product and save
	 driver.findElement(By.linkText("Products")).click();
	 driver.findElement(By.xpath("//img[@alt=\"Create Product...\"]")).click();
	 driver.findElement(By.name("productname")).sendKeys(prodNamerand);
	 driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
	 
	 // s6:verify for product
	String prodheader = driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
	if (prodheader.contains(prodNamerand)) {
		System.out.println(prodheader);
		System.out.println("product successfully created");
	}
	else {
		System.out.println(prodheader);
		System.out.println("product not created");
	}
	 
	 //s7:create the campaign with more link
	 driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]")).click();
	 driver.findElement(By.name("Campaigns")).click();
	 driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
	 driver.findElement(By.name("campaignname")).sendKeys(campNameRand);
	 
	 //s8:add the product to campaign
	 
	
	 driver.findElement(By.xpath("//img[@alt=\"Select\"]")).click();
	 Set<String> win1 = driver.getWindowHandles();
	 for(String newt:win1) {
		 driver.switchTo().window(newt);
	 }
	 
	 driver.findElement(By.id("search_txt")).sendKeys(prodNamerand);
	 driver.findElement(By.name("search")).click();
	 driver.findElement(By.xpath("//a[text()='"+prodNamerand+"']")).click();
	 
	Set<String> win2 = driver.getWindowHandles();
		for(String oldt : win2)
		{
			driver.switchTo().window(oldt);
		}

	//s: save the campaign
	 driver.findElement(By.xpath("//input[@class=\"crmButton small save\"]")).click();
	 
	 //verify the campaign creation
	 String campheader = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
	 if (campheader.contains(campNameRand)) {
		System.out.println(campheader);
		System.out.println("campaign created successfully");
	}
	 else {
		System.out.println(campheader);
		System.out.println("campaign not created");
	}
	 //s: logout from the app
	 WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	 Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	 
	 
	
}
}
