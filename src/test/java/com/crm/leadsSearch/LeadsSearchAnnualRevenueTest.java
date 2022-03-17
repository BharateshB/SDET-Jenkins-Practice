package com.crm.leadsSearch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.reporters.PojoReporterConfig;

public class LeadsSearchAnnualRevenueTest {
@Test
public void leadsSearchAnnualRevenueTest() throws Throwable {
	
	/*S1: READ ENTIRE DATA*/
	
	//S1.1:READ FROM PROPERTY FILE
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	Properties pobj= new Properties();
	pobj.load(fis);
	String BROWSER = pobj.getProperty("browser");
	String URL = pobj.getProperty("url");
	String USERNAME = pobj.getProperty("username");
	String PASSWORD = pobj.getProperty("password");
	
	//S1.2:READ FROM EXCEL
	FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\leads_test.xlsx");
	Workbook wb = WorkbookFactory.create(fi);
	Sheet sh = wb.getSheet("leadsSearchSal");
	Row ro = sh.getRow(1);
	Cell cv1 = ro.getCell(1);
	String SALUTATION = cv1.getStringCellValue();
	
	Cell cv2 = ro.getCell(2);
	String COMPARISONTERM = cv2.getStringCellValue();
	
	Cell cv3 = ro.getCell(3);
	double CELLVALUE = cv3.getNumericCellValue();
	int CV=(int)CELLVALUE;
	Integer integer=CV;
	String SV=Integer.toString(integer);
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
	driver.manage().window().maximize();
	
	/*S3:LOAD THE URL*/
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	s.selectByVisibleText(SALUTATION);
	
	//S6.2:SELECT EQUALS FOR COMPARISON
	WebElement ele2 = driver.findElement(By.id("fop0"));
	Select s2=new Select(ele2);
	s2.selectByVisibleText(COMPARISONTERM);
	
	//S6.3: PUT COMPARISON VALUE
	driver.findElement(By.id("fval0")).clear();
	driver.findElement(By.id("fval0")).sendKeys(SV);
	
	//S6.3;CLICK ON SEARCH BUTTON
	driver.findElement(By.xpath("//input[@onclick=\"callSearch('Advanced');\"]")).click();
	Thread.sleep(3000);
	
	/*S7: LOGOUT FROM THE APP*/
	WebElement ele3 = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	Actions a= new Actions(driver);
	a.moveToElement(ele3).click().perform();
	
	driver.findElement(By.className("drop_down_usersettings")).click();
	 
	driver.quit();
}

private int Integer(double cELLVALUE) {
	// TODO Auto-generated method stub
	return 0;
}
}
