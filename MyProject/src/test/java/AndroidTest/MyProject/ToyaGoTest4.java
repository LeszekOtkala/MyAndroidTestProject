package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import pages.MainMenu;
import pages.TvPlayerPage;
/*
 * Test sprawdza czy podczas włączania i wyłączania Wifi aplikacja jest stabilna
 * do uruchomienia testu powinny być włączone wifi i transmisja danych
 * 
 */
public class ToyaGoTest4{
	
	
	public static AndroidDriver driver;
	private String currentText;
	private MainMenu mainMenu;
	private TvPlayerPage tvPlayerPage;
	
	@Test
	public void test4() throws InterruptedException, MalformedURLException {
	
		
		driver=new CreateDriver().getDriver();
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		mainMenu= new MainMenu(driver);
		
		int j=0;
		
		while(!mainMenu.getMainTextViewText().equals("Oglądaj TV")&&j<30) {
			
			System.out.println(mainMenu.getMainTextViewText());
			currentText=mainMenu.getMainTextViewText();
			mainMenu.swipeRight();
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
			j++;
		}
		
		mainMenu.activeMenuItemClick();
		tvPlayerPage= new TvPlayerPage(driver);
		
		for(int i=0;i<10;i++) {
			
			driver.toggleWifi();
			System.out.println("Przełączono Wi-Fi");
			Thread.sleep(2000);
			
			try {
				Assert.assertTrue("Nie znaleziono elementów odtwarzacza ",tvPlayerPage.isVideoDisplayed()&&tvPlayerPage.isSeekBarDisplayed());
			
			}
			catch(NoSuchElementException ex){
				Assert.assertTrue("Nie znaleziono elementów odtwarzacza ",false);
			}
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

