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
/*
 * Test sprawdza czy podczas włączania i wyłączania Wifi aplikacja jest stabilna
 * do uruchomienia testu powinny być włączone wifi i transmisja danych
 * 
 */
public class ToyaGoTest4{
	
	@After
	public void quitDriver(){
		driver.quit();
	}
	
	public static AndroidDriver driver;
	private String currentText;
	
	@Test
	public void test4() throws InterruptedException, MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		
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
		
		int j=0;
		while(!mainTextView.getAttribute("text").equals("Oglądaj TV")&&j<30) {
			System.out.println(mainTextView.getAttribute("text"));
			currentText=mainTextView.getAttribute("text");
			swipeRight();
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
			j++;
			}
		activeMenuItem.click();
		
		for(int i=0;i<10;i++) {
			driver.toggleWifi();
			System.out.println("Przełączono Wi-Fi");
			
			Thread.sleep(2000);
			
		try {
			
			MobileElement video=(MobileElement) driver.findElementById("com.toya.toyago:id/video");
			MobileElement seekBar=(MobileElement) driver.findElementById("com.toya.toyago:id/playerSeekBar");
			//System.out.println("Nie było Alertu, Odtwarzacz jest wyświetlony! ");
			
			Assert.assertTrue("Nie znaleziono elementów odtwarzacza ",video!=null&&seekBar!=null);
			
			}
			catch(NoSuchElementException ex)
			{
				//System.out.println("Nie znaleziono co najmniej jednego elementu odtwarzacza");
				
				Assert.assertTrue("Nie znaleziono elementów odtwarzacza ",false);
			}
		}
		
		
		
	}
	public void swipeLeft() {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(700, 850));
		action.moveTo(PointOption.point(400, 850));
		action.release();
		action.perform();

	}
	public void swipeRight() {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(700, 850));
		action.moveTo(PointOption.point(900, 850));
		action.release();
		action.perform();

	}
	
}

