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
/*
 * Test sprawdza czy podczas włączania i wyłączania poszczególnych funkcjonalnosi aplikacja jest stabilna
 * 
 */
public class ToyaGoTest3 {
	
	
	
	public static AndroidDriver driver;
	private String currentText;
	
	@Test
	public void test3() throws InterruptedException, MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		//capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability("deviceName", "3300628333e3a29b");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.toya.toyago");
		capabilities.setCapability("appActivity", "com.toya.toyago.MainActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		MobileElement mainTextView=(MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
		MobileElement activeMenuItem = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView");
		
		try {
		for(int i=0;i<30;i++) {
			System.out.println(mainTextView.getAttribute("text"));
			if(!mainTextView.getAttribute("text").equals("Pilot")) {
				if(mainTextView.getAttribute("text").equals("Oglądaj TV")) {
					activeMenuItem.click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.toya.toyago:id/playerBackButton")));
					MobileElement playerBackButton = (MobileElement) driver.findElementById("com.toya.toyago:id/playerBackButton");
					playerBackButton.click();
					currentText=mainTextView.getAttribute("text");
					swipeRight();
					wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
				}
				else {
				activeMenuItem.click();
				
				
				if(driver.isDeviceLocked())
					driver.unlockDevice();
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("back")));
				MobileElement backButton = (MobileElement) driver.findElementByAccessibilityId("back");
				backButton.click();
				
				for(int j=0;j<3;j++){
					currentText=mainTextView.getAttribute("text");
					swipeRight();
					wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
					}
				
				}
			}
			else{
				currentText=mainTextView.getAttribute("text");
				swipeRight();
				wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
			}
		}
		
		Assert.assertTrue(mainTextView.isDisplayed()&&activeMenuItem.isDisplayed());
		}
		catch(Exception e) {
		
			e.printStackTrace();
			Assert.assertTrue("Aplikacja nie działa poprawnie",mainTextView.isDisplayed()&&activeMenuItem.isDisplayed());
		}
		
		
		
		
	}
	public void swipeRight() {
		
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(700, 850));
		action.moveTo(PointOption.point(900, 850));
		action.release();
		action.perform();

	}
	
	
	@After
	public void quitDriver(){
		if(driver!=null) {
			driver.quit();
			driver=null;
		}	
	}
	
}

