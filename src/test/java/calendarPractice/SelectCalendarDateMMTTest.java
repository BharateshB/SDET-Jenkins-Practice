package calendarPractice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.internal.AbstractParallelWorker.Arguments;

import com.crm.GenericLibrary.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

public class SelectCalendarDateMMTTest {

	@Test
	public void calendarIbibo() throws Throwable {
		String date="16";
		String monthAndyear="November 2022";
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();
		
		driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10']/ancestor::label[@for='departure']")).click();
		//WebElement ele = driver.findElement(By.xpath("//div[text()='"+monthAndyear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']"));
		
		//WebDriverUtility wlib= new WebDriverUtility();
		//wlib.scrollAction(driver);
		for(;;) {
		try {
			WebElement ele = driver.findElement(By.xpath("//div[text()='"+monthAndyear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']"));
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			jsExecutor.executeScript("arguments[0].click();", ele);
			break; 
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}
		}
		Thread.sleep(3000);
	}
}
