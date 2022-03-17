package calendarPractice;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.apache.poi.ss.formula.functions.Count;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Calendaribibo {

	@Test
	public void calendarIbibo() throws Throwable {
		
		String month = "April";
		String year = "2023";
		String day="25";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.goibibo.com/");
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveByOffset(10, 10).click().perform();
		WebElement cal = driver.findElement(By.xpath("//span[@class='sc-kfPuZi dprjVP fswDownArrow']"));
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(cal));
		cal.click();
		 List<WebElement> monthyear = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']"));
		for(;;) {
			for(WebElement ele:monthyear) {
			
			if(ele.getText().equalsIgnoreCase(month+" "+year)) {
				driver.findElement(By.xpath("//div[text()='"+month+" "+ year+"']/../..//div[text()='"+day+"']")).click();
				break;
			}
			else {
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
				Thread.sleep(2000);
				}
			}
		}		
	}
	
}