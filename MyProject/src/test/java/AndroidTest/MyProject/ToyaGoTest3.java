package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import pages.CategoryPage;
import pages.MainMenu;
import pages.TvPlayerPage;
/*
 * Test sprawdza czy podczas włączania i wyłączania poszczególnych funkcjonalnosi aplikacja jest stabilna
 * 
 */
public class ToyaGoTest3 {
	
	public static AndroidDriver driver;
	private String currentText;
	private MainMenu mainMenu;
	private CategoryPage categoryPage;
	private TvPlayerPage tvPlayerPage;
	
	
	@Test
	public void test3() throws InterruptedException, MalformedURLException {
		
		driver=new CreateDriver().getDriver();
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		mainMenu=new MainMenu(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		tvPlayerPage= new TvPlayerPage(driver);
		categoryPage=new CategoryPage(driver);
		try {
		for(int i=0;i<30;i++) {
			System.out.println(mainMenu.getMainTextViewText());
			if(!mainMenu.getMainTextViewText().equals("Pilot")) {
				
				if(mainMenu.getMainTextViewText().equals("Oglądaj TV")) {
					mainMenu.activeMenuItemClick();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(tvPlayerPage.getTvPlayerBackButton()));
					tvPlayerPage.tvPlayerBackButtonClick();
					currentText=mainMenu.getMainTextViewText();
					mainMenu.swipeRight();
					wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
				}
				else {
				
				mainMenu.activeMenuItemClick();
				
			
				wait.until(ExpectedConditions.visibilityOfElementLocated(categoryPage.getBackButton()));
				categoryPage.backButtonClick();
				
				for(int j=0;j<3;j++){
					currentText=mainMenu.getMainTextViewText();
					mainMenu.swipeRight();
					wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
					}
				
				}
			}
			else{
				currentText=mainMenu.getMainTextViewText();
				mainMenu.swipeRight();
				wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
			}
		}
		
		Assert.assertTrue(mainMenu.getMainTextView().isDisplayed()&&mainMenu.getActiveMenuItem().isDisplayed());
		}
		catch(Exception e) {
		
			e.printStackTrace();
			Assert.assertTrue("Aplikacja nie działa poprawnie",false);
		}

	}
	
	
	@After
	public void quitDriver(){
		if(driver!=null) {
			driver.quit();
			driver=null;
		}	
	}
	
}

