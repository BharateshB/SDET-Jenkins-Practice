package com.crm.opportunityTests;

import javax.swing.GroupLayout.Group;

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

public class CreateOpportunityUtilTest {
@Test
public void createOpportunityUtilTest() throws Throwable {
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
		String opportunityName = eLib.readDataFromExcel("opportunities", 1, 2)+"_"+jLib.getRandomNumber();
		String contactname = eLib.readDataFromExcel("opportunities", 1, 4)+"_"+jLib.getRandomNumber();;
		String campaignName = eLib.readDataFromExcel("opportunities", 1, 6)+"_"+jLib.getRandomNumber();
		String relatedTo = eLib.readDataFromExcel("opportunities", 1, 3);
		String leadSource = eLib.readDataFromExcel("opportunities", 1, 5);
	
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
		 
		 //s4: Create navigate to contacts
		 driver.findElement(By.linkText("Contacts")).click();
		 driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		 
		 //s5:create a new contact and save
		 driver.findElement(By.name("lastname")).sendKeys(contactname);
		 driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		 
		 //S6:verify the contact
		 String contHeader = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		 if (contHeader.contains(contactname)) {
			System.out.println(contHeader);
			System.out.println("contact created successfully");
		}
		 else {
			System.out.println(contHeader);
			System.out.println("campaign not created");
		 }
		 
		//s6:create the campaign with more link
		 driver.findElement(By.xpath("//img[@src=\"themes/softed/images/menuDnArrow.gif\"]")).click();
		 driver.findElement(By.name("Campaigns")).click();
		 driver.findElement(By.xpath("//img[@title=\"Create Campaign...\"]")).click();
		 driver.findElement(By.name("campaignname")).sendKeys(campaignName);
		
		 //s7: save the campaign
		 driver.findElement(By.xpath("//input[@class=\"crmButton small save\"]")).click();
		 
		//S8:verify the campaign creation
		 String campheader = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		 if (campheader.contains(campaignName)) {
			System.out.println(campheader);
			System.out.println("campaign created successfully");
		}
		 else {
			System.out.println(campheader);
			System.out.println("campaign not created");
		 
		 }
			//S9:Click on opportunities Link
			driver.findElement(By.linkText("Opportunities")).click();
			
			//S10:Create the opportunity
			driver.findElement(By.xpath("//img[@title=\"Create Opportunity...\"]")).click();
			
			driver.findElement(By.name("potentialname")).sendKeys(opportunityName);
			
			WebElement ele = driver.findElement(By.id("related_to_type"));
			wLib.select(relatedTo, ele);
			
			driver.findElement(By.xpath("//img[@src=\'themes/softed/images/select.gif\']")).click();
			wLib.switchtowindow(driver, "Contacts");
			
			driver.findElement(By.id("search_txt")).sendKeys(contactname);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()=' "+contactname+"']")).click();
			Thread.sleep(2000);
			wLib.switchtowindow(driver,"Potentials");
			
			WebElement ele3 = driver.findElement(By.name("leadsource"));
			wLib.select(leadSource, ele3);
			driver.findElement(By.xpath("//input[@name='campaignname']/following-sibling::img[@src='themes/softed/images/select.gif']")).click();
			//driver.findElement(By.xpath("(//img[@src=\"themes/softed/images/select.gif\"])[2]")).click();
			wLib.switchtowindow(driver, "Campaigns");
			driver.findElement(By.id("search_txt")).sendKeys(campaignName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[text()='"+campaignName+"']")).click();
			Thread.sleep(2000);
			wLib.switchtowindow(driver,"Potentials");
			
			//save
			driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
			
			//S8:verify the opportunity creation
			 String oppotunityHeader = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
			 if (oppotunityHeader.contains(opportunityName)) {
				System.out.println(opportunityName);
				System.out.println("opportunity created successfully");
			}
			 else {
				System.out.println(opportunityName);
				System.out.println("opportunity not created");
			 }
		 //s:logout from the application
		 
		 WebElement ele2 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 wLib.mouseHoverSingleClick(driver, ele2);
		 driver.findElement(By.className("drop_down_usersettings")).click();
		 driver.quit();
		}
	}