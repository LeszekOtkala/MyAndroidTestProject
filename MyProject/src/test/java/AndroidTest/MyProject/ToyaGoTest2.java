package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class ToyaGoTest2 {
	
	public static AndroidDriver driver;
	
	@Test
	public void test2() throws InterruptedException, MalformedURLException {
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
		//(new TouchAction(driver)).press(pressOptions).moveTo({x: 891: y: 894}).release().perform();
		//włączanie i wyłączanie wifi
		//driver.toggleWifi();
		//włączanie i wyłączanie danych komórkowych
		//driver.toggleData();
		
		
		MobileElement mainTextView=(MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
		
		while(!mainTextView.getAttribute("text").equals("Ustawienia")) {
		System.out.println(mainTextView.getAttribute("text"));
		swipeBy(700,850,900,850);
		Thread.sleep(500);
		}
		
		
		MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView");
		
		el1.click();
		
		
		MobileElement el2 = (MobileElement) driver.findElementById("com.toya.toyago:id/allow_3g");
		System.out.println(el2.getAttribute("checked"));
		if(el2.getAttribute("checked").equals("true"))
			el2.click();
		
		System.out.println(el2.getAttribute("checked"));
		if(el2.getAttribute("checked").equals("false")) {
			
			MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("back");
			el3.click();
		}
		while(!mainTextView.getAttribute("text").equals("Oglądaj TV")) {
			System.out.println(mainTextView.getAttribute("text"));
			swipeBy(700,850,400,850);
			Thread.sleep(500);
			}
		el1.click();
		
		
		
			try {
				MobileElement el5 = (MobileElement) driver.findElementById("android:id/alertTitle");
				System.out.println(el5.getAttribute("text"));
				
				MobileElement el6 = (MobileElement) driver.findElementById("android:id/message");
				//System.out.println("Wyśwetlono alert:\n"+el5.getAttribute("text")+"\n"+el6.getAttribute("text"));
				
				MobileElement el7 = (MobileElement) driver.findElementById("android:id/button1");
				//System.out.println(el7.getAttribute("text"));
				String alert="\nWyśwetlono alert:\n"+el5.getAttribute("text")+"\n"+el6.getAttribute("text");
				el7.click();
				driver.quit();
				//Assert.assertFalse(alert, true);
				Assert.assertTrue(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//System.out.println("Nie było Alertu!");
				try {
					MobileElement video=(MobileElement) driver.findElementById("com.toya.toyago:id/video");
					MobileElement seekBar=(MobileElement) driver.findElementById("com.toya.toyago:id/playerSeekBar");
					//System.out.println("Nie było Alertu, Odtwarzacz sie uruchomił ");
					driver.quit();
					Assert.assertTrue("Nie było Alertu, Odtwarzacz sie uruchomił ",false);
					}
					catch(NoSuchElementException ex)
					{
						//System.out.println("Nie znaleziono co najmniej jednego elementu odtwarzacza");
						driver.quit();
						Assert.assertTrue(true);
					}
				
				
			}
		
			
		
		
		
		
		//System.out.println(seekBar.getAttribute("bounds"));

		//driver.wait(20);
		driver.quit();
		
	}
	public void swipeBy(int startX, int startY, int stopX, int stopY) {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(startX, startY));
		action.moveTo(PointOption.point(stopX, stopY));
		action.release();
		action.perform();

	}
	
}

