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

public class ToyaGoTest1 {
	/*
	 *  Test sprawdza czy po zaznaczeniu checkboxa zezwalającego na odtwarzanie, odtwarzacz się uruchomi
	 *   i niewyświetlony zostanie komunikat o konieczności włączenia wifi
	 *   dla uruchomienia yestu Wifi powinno być wyłączone, a dane komórkowe włączone
	 */
	
	public static AndroidDriver driver;
	
	@Test
	public void test1() throws InterruptedException, MalformedURLException {
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
		
		
		
		
		MobileElement mainTextView=(MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
		
		while(!mainTextView.getAttribute("text").equals("Ustawienia")) {
		System.out.println(mainTextView.getAttribute("text"));
		swipeBy(700,850,900,850);
		Thread.sleep(500);
		}
		
		
		MobileElement activeMenuItem = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView");
		
		activeMenuItem.click();
		
		
		MobileElement allowDataCheckBox = (MobileElement) driver.findElementById("com.toya.toyago:id/allow_3g");
		System.out.println(allowDataCheckBox.getAttribute("checked"));
		if(allowDataCheckBox.getAttribute("checked").equals("false"))
			allowDataCheckBox.click();
		
		System.out.println(allowDataCheckBox.getAttribute("checked"));
		if(allowDataCheckBox.getAttribute("checked").equals("true")) {
			
			MobileElement backButton = (MobileElement) driver.findElementByAccessibilityId("back");
			backButton.click();
		}
		while(!mainTextView.getAttribute("text").equals("Oglądaj TV")) {
			System.out.println(mainTextView.getAttribute("text"));
			swipeBy(700,850,400,850);
			Thread.sleep(500);
			}
		activeMenuItem.click();
		
		
		
			try {
				MobileElement alertTitle = (MobileElement) driver.findElementById("android:id/alertTitle");
				System.out.println(alertTitle.getAttribute("text"));
				
				MobileElement alertMessage = (MobileElement) driver.findElementById("android:id/message");
				//System.out.println("Wyśwetlono alert:\n"+alertTitle.getAttribute("text")+"\n"+alertMessage.getAttribute("text"));
				
				MobileElement alertBackButton = (MobileElement) driver.findElementById("android:id/button1");
				//System.out.println(alertBackButton.getAttribute("text"));
				String alert="\nWyśwetlono alert:\n"+alertTitle.getAttribute("text")+"\n"+alertMessage.getAttribute("text");
				alertBackButton.click();
				driver.quit();
				Assert.assertFalse(alert, true);
				
				//Assert.assertTrue(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//System.out.println("Nie było Alertu!");
				try {
					MobileElement video=(MobileElement) driver.findElementById("com.toya.toyago:id/video");
					MobileElement seekBar=(MobileElement) driver.findElementById("com.toya.toyago:id/playerSeekBar");
					//System.out.println("Nie było Alertu, Odtwarzacz sie uruchomił ");
					Assert.assertTrue(true);
					}
					catch(NoSuchElementException ex)
					{
						//System.out.println("Nie znaleziono co najmniej jednego elementu odtwarzacza");
						driver.quit();
						Assert.assertTrue("Nie znaleziono co najmniej jednego elementu odtwarzacza",false);
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

