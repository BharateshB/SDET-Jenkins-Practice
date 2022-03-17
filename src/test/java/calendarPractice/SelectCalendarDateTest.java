package calendarPractice;

import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SelectCalendarDateTest {

	@Test
	
	public void calendarDate() throws Throwable {
		String date="16";
		String monthAndyear="November 2022";
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();
		Thread.sleep(2000);
		WebElement calendar = driver.findElement(By.xpath("//span[@class='sc-kfPuZi dprjVP fswDownArrow']"));
		calendar.click();
		for(;;) {
		try {
			driver.findElement(By.xpath("//div[text()='"+monthAndyear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']")).click();
			break;
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
		}
		}
		Thread.sleep(3000);
		driver.quit();
	}
}
