package com.crm.GenericLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility {

	/**
	 * this method will maximise the window
	 * @param driver
	 */
	public void maximiseWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * this method will wait for 20 seconds to load the page
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * this method will wait for 10 seconds for element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	/**
	 * this method will wait for 20 sec to element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	/**
	 * this method will select the dropdown option by selecting index
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index) {
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * this method will select the dropdown option by selecting value
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String value) {
		Select sel= new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * this method will select the dropdown option by selecting visible text
	 * @param value
	 * @param element
	 */
	public void select(String value, WebElement element) {
		Select sel= new Select(element);
		sel.selectByVisibleText(value);
	}
	
	/**
	 * this method is used to perform mouse hover action and click on selected webelement
	 * @param driver
	 * @param element
	 */
	public void mouseHoverSingleClick(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * this method is used to perform mouse hover action and double click on selected webelement
	 * @param driver
	 * @param element
	 */
	public void mouseHoverDoubleClick(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).doubleClick().perform();
	}
	
	/**
	 * this method is used to perform mouse hover action and double click on anywhere
	 * @param driver
	 * @param element
	 */
	public void mouseHoverDoubleClick(WebDriver driver) {
		Actions act= new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 * this method is used to perform mouse hover action and drag and drag operation on selected webelement
	 * @param driver
	 * @param srcElement
	 * @param targetElement
	 */
	public void mouseHoverD(WebDriver driver, WebElement srcElement,WebElement targetElement ) {
		Actions act= new Actions(driver);
		act.dragAndDrop(srcElement, targetElement).perform();
	}
	
	/**
	 * this method is used to perform mouse hover action and right click on selected webelement
	 * @param driver
	 * @param element
	 */
	public void mouseHoverRightClick(WebDriver driver, WebElement element) {
		Actions act= new Actions(driver);
		act.moveToElement(element).contextClick().perform();
	}
	
	/**
	 * this method is used to perform mouse hover action and right click on anywhere
	 * @param driver
	 * @param element
	 */
	public void mouseHoverRightClick(WebDriver driver) {
		Actions act= new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * this method will press enter key using actions class 
	 * @param driver
	 */
	public void enterkeypress(WebDriver driver) {
		Actions actions= new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * this method will press the enter key
	 * @throws Throwable
	 */
	public void enterkeys() throws Throwable {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 * this method will release the enter key
	 * @throws Throwable
	 */
	
	public void enterkeyrelease() throws Throwable {
		Robot r= new Robot();
		r.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	/**
	 * This method will switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchtoframe(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * this method will switch to frame based on name or ID 
	 * @param driver
	 * @param numeOrId
	 */
	public void switchtoframe(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This methods will switch to frame based on webelement address 
	 * @param driver
	 * @param element
	 */
	public void switchtoframe(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * this method is used to accept the alert pop up
	 * @param driver
	 */
	public void acceptalert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * this method is used to cancel the alert pop up
	 * @param driver
	 */
	public void cancelalert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	
	/**
	 * this method is used to switch the windows and get the title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void switchtowindow(WebDriver driver, String partialWindowTitle) {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		while(it.hasNext()){
			String winId = it.next();
			
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			
			if(currentWinTitle.contains(partialWindowTitle))
			{
				break;
			}
		}
		
	}
	
	
	/**
	 * this method is used to take the screenshot and store
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable
	 */
	public String getscreenshot(WebDriver driver, String screenShotName) throws Throwable {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String pathString=".\\Screenshot\\"+screenShotName+".png";
		File dest = new File(pathString);
		Files.copy(src, dest);
		return pathString;
	}
	
	
	/**
	 * this method is used to scroll the windows
	 * @param driver
	 */
	public void scrollAction(WebDriver driver) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)", "");
	}
	
	/**
	 * this method is used to scroll the windows until element
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}
}