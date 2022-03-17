package calendarPractice;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.WebDriverUtility;

public class CalendarTodayDateSelectTest {

	@Test
	
	public void calendarTodaysDate() throws Throwable {
		
		LocalDateTime lDate=  LocalDateTime.now();
		String calMonth = lDate.getMonth().name();
		int idate = lDate.getDayOfMonth();
		int iYear= lDate.getYear();
		Integer IDate=idate;
		String date=IDate.toString();
		Integer IYear=iYear;
		String year= IYear.toString();
		String month=calMonth.substring(0, 1).toUpperCase()+calMonth.substring(1).toLowerCase();
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
		
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//span[@class='langCardClose']/ancestor::div[@class='langCard  fixedCard bounceAni']")).click();
		
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();
		
		
		driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10']/ancestor::label[@for='departure']")).click();
		WebDriverUtility wlib= new WebDriverUtility();
		wlib.scrollAction(driver);
		for(;;) {
		try {
			
			driver.findElement(By.xpath("//div[text()='"+month+" "+ year+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']")).click();
			Thread.sleep(2000);
			break;
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}
		}
		driver.quit();
	}
}

