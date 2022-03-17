package com.crm.OrganizationTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class CreateOrganizationWithPropertyFileTest {
@Test
	public void createOrganizationWithPropertyFileTest() throws Throwable {
	{
		//Step 1: read data from property file 
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties pObj = new Properties();
				pObj.load(fis);
				String BROWSER = pObj.getProperty("browser");
				String URL = pObj.getProperty("url");
				String USERNAME = pObj.getProperty("username");
				String PASSWORD=pObj.getProperty("password");
				
				//Step 2: launch the browser
				WebDriver driver = null;
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					driver = new ChromeDriver();
				}
				else if(BROWSER.equalsIgnoreCase("firefox"))
				{
					driver = new FirefoxDriver();
				}
				else
				{
					System.out.println("invalid browser");
				}
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.get(URL);
				
				//Step 3: lon
		        driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		        driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		        driver.findElement(By.id("submitbutton")).click();
		        Thread.sleep(3000);
				//Step 3: navigate to organizations link
				driver.findElement(By.linkText("Organizations")).click();
				Thread.sleep(3000);
				//Step 4: click on create Organization link I'm
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				Thread.sleep(3000);
				//Step 5:create org with mandatory fields
				driver.findElement(By.name("accountname")).sendKeys("ALL STATES");
				Thread.sleep(3000);
				//Step 6: Save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(3000);
				driver.quit();
				
			}

}
	
}
