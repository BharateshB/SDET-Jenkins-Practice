package com.crm.GenericLibrary;

import java.nio.channels.NonWritableChannelException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	// create object of all utilities
	public JDBCutility dbLib = new JDBCutility();
	public ExcelFileUtilityJava eLib= new ExcelFileUtilityJava();
	public JavaUtility jLib =new JavaUtility();
	public PropertyFileUtility pLib=new PropertyFileUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver;
	@BeforeSuite
	//@BeforeSuite(groups = {"smokeSuite","regressionSuite"})
	public void connectDataBase() {
		//dbLib.databaseConnection();
		Reporter.log("database connection successful",true);
	}
	//@Parameters("browser")
	@BeforeTest
	public void parallelexecution() {
		System.out.println("parallel execution");	
	}
	
	
	
	//@BeforeClass(groups = {"smokeSuite","regressionSuite"})
	@BeforeClass
	public void launchTheBrowser() throws Throwable{
		//read data from property file
		String BROWSER = pLib.readDataFromPropertyFile("browser");
		String URL = pLib.readDataFromPropertyFile("url");
		
		//Create run time polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else {
			System.out.println("invalid browser");
		}
		sdriver=driver;
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		Reporter.log("browser launched successfully",true);
	}
	@BeforeMethod
	//@BeforeMethod(groups = {"smokeSuite","regressionSuite"})
	public void login() throws Throwable {
		//read data from property file
		String USERNAME = pLib.readDataFromPropertyFile("username");
		String PASSWORD = pLib.readDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver); 
		lp.loginToApp(USERNAME, PASSWORD);
		Reporter.log("login successful",true);
	}
	
	@AfterMethod
	//@AfterMethod(groups = {"smokeSuite","regressionSuite"})
	public void logout() {
		Homepage hp = new Homepage(driver);
		hp.signoutOfApp(driver);
		Reporter.log("logout successful",true);
		
	}
	
	@AfterClass
	//@AfterClass(groups = {"smokeSuite","regressionSuite"})
	public void closeBrowser() {
		driver.quit();
		Reporter.log("browser exit successful",true);
	}
	@AfterTest
	public void parallelexecutionend() {
		System.out.println("parallel execution ended");	
	}

	@AfterSuite(groups = {"smokeSuite","regressionSuite"})
	public void closeDB() throws Throwable {
		//dbLib.closeConnection();
		Reporter.log("database close successful",true);
	}
	
	
}	
