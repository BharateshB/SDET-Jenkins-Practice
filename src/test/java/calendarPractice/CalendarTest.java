package calendarPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.WebDriverUtility;
import com.crm.objectRepository.Homepage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarTest extends WebDriverUtility {

	@Test
	public void calendarTest() throws Throwable {
		
		String monthString = "February";
		int day= 28;
		int year = 2022;
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.cleartrip.com/");
		
		/*LoginPage lp= new LoginPage(driver);
		lp.loginToApp("admin", "admin");
		
		Homepage hp= new Homepage(driver);
		driver.findElement(By.linkText("Products")).click();
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.name("productname")).sendKeys("dell");
		
		driver.findElement(By.xpath("//img[src='themes/softed/images/btnL3Calendar.gif']")).click();
		
		String month = driver.findElement(By.xpath("((//div[@class ='calendar'])[2]//tr/td[2])[1]")).getText();
		
		if(month.contains("February")) {
			driver.findElement(By.xpath(month)))
		}*/
		
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//div[contains(@class,'homeCalender')]")).click();
		scrollAction(driver);
		driver.findElement(By.xpath("//button[@style='min-width: 250px;']")).click();
		driver.findElement(By.xpath("//div[text()='"+monthString+"' '"+year+"']/../..//div[text()='"+day+"']")).click();
		
		
	}
}
