package com.crm.PRACTICE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PomPracticeForLogin {
	@Test
	
	public void pomPracticeLogin(){
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp("admin", "admin");
		
		Homepage hp= new Homepage(driver);
		hp.ClickOnContactsLnk();
		
		hp.signoutOfApp(driver);
	}
}
