package com.crm.LeadsSearchwithutilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.ExcelFileUtilityJava;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;
import com.crm.GenericLibrary.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeadsSearchLeadSrcUtil {
@Test
public void leadsSearchLeadSrcUtil() throws Throwable {
	WebDriverManager.chromedriver().setup();
	WebDriverManager.firefoxdriver().setup();
	
	/*read data*/
	PropertyFileUtility pLib = new PropertyFileUtility();
	JavaUtility jLib = new JavaUtility();
	ExcelFileUtilityJava eLib = new ExcelFileUtilityJava();
	WebDriverUtility wLib = new WebDriverUtility();
	
	/*Step 1: read all neccessary data*/
	String BROWSER = pLib.readDataFromPropertyFile("browser");
	String URL = pLib.readDataFromPropertyFile("url");
	String USERNAME = pLib.readDataFromPropertyFile("username");
	String PASSWORD = pLib.readDataFromPropertyFile("password");
	
	String Salutation = eLib.readDataFromExcel("leadsSearchLeadSrc", 1, 1);
	String ComparisonTerm = eLib.readDataFromExcel("leadsSearchLeadSrc", 1, 2);
	String ComparisonValue = eLib.readDataFromExcel("leadsSearchLeadSrc", 1, 3);
	
	/*S2: LAUNCH THE BROWSER*/
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
	/*S3:LOAD THE URL*/
	
	driver.get(URL);
	
	/*S4:LOGIN TO THE APP*/
	
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	/*S5: CLICK ON TO LEADS LINK*/
	driver.findElement(By.linkText("Leads")).click();
	
	/*S6:ADVANCE SEARCH LEADS BY ANNUAL INCOME*/
	
	//S6.1:CLICK ON ADVANCED SEARCH
	driver.findElement(By.xpath("//a[text()='Go to Advanced Search']")).click();
	
	//S6.2:SELECT ANNUAL INCOME FROM SALUTATION DROP DOWN MENU
	WebElement ele = driver.findElement(By.xpath("//select[@class=\"detailedViewTextBox\"]"));
	Thread.sleep(3000);
	Select s = new Select(ele);
	Thread.sleep(3000);
	s.selectByVisibleText(Salutation);
	
	//S6.2:SELECT EQUALS FOR COMPARISON
	WebElement ele2 = driver.findElement(By.id("fop0"));
	wLib.select(ComparisonTerm, ele2);
	
	//S6.3: PUT COMPARISON VALUE
	driver.findElement(By.id("fval0")).clear();
	driver.findElement(By.id("fval0")).sendKeys(ComparisonValue);
	
	//S6.3;CLICK ON SEARCH BUTTON
	driver.findElement(By.xpath("//input[@onclick=\"callSearch('Advanced');\"]")).click();
	Thread.sleep(3000);
	
	/*S7: LOGOUT FROM THE APP*/
	WebElement ele3 = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	wLib.mouseHoverSingleClick(driver, ele3);
	
	driver.findElement(By.className("drop_down_usersettings")).click();
	 
	driver.quit();
}
}
