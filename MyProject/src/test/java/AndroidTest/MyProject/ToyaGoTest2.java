package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

/*
 * Test sprawdza czy po odznaczeniu checkboxa zezwalającego na odtwarzanie wyświetlony zostanie komunikat o konieczności włączenia wifi
 * dla uruchomiena testu WiFi powinno być wyłączone, a dane komórkowe włączone
 */

public class ToyaGoTest2 {
	
	@After
	public void quitDriver(){
		driver.quit();
	}
	
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
		
		
		MobileElement activeMenuItem = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView");
		
		activeMenuItem.click();
		
		
		MobileElement allowDataCheckBox = (MobileElement) driver.findElementById("com.toya.toyago:id/allow_3g");
		System.out.println(allowDataCheckBox.getAttribute("checked"));
		if(allowDataCheckBox.getAttribute("checked").equals("true"))
			allowDataCheckBox.click();
		
		System.out.println(allowDataCheckBox.getAttribute("checked"));
		if(allowDataCheckBox.getAttribute("checked").equals("false")) {
			
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
				//System.out.println("Wyśwetlono alert:\n"+el5.getAttribute("text")+"\n"+alertMessage.getAttribute("text"));
				
				MobileElement alertBackButton = (MobileElement) driver.findElementById("android:id/button1");
				//System.out.println(el7.getAttribute("text"));
				String alert="\nWyśwetlono alert:\n"+alertTitle.getAttribute("text")+"\n"+alertMessage.getAttribute("text");
				alertBackButton.click();
				
				
				Assert.assertTrue(false);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//System.out.println("Nie było Alertu!");
				try {
					MobileElement video=(MobileElement) driver.findElementById("com.toya.toyago:id/video");
					MobileElement seekBar=(MobileElement) driver.findElementById("com.toya.toyago:id/playerSeekBar");
					//System.out.println("Nie było Alertu, Odtwarzacz sie uruchomił ");
					
					Assert.assertTrue("Nie było Alertu, Odtwarzacz sie uruchomił ",false);
					}
					catch(NoSuchElementException ex)
					{
						//System.out.println("Nie znaleziono co najmniej jednego elementu odtwarzacza");
						
						Assert.assertTrue(true);
					}
				
				
			}
		
			
		
		
		
		
		//System.out.println(seekBar.getAttribute("bounds"));

		//driver.wait(20);
		
		
	}
	public void swipeBy(int startX, int startY, int stopX, int stopY) {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(startX, startY));
		action.moveTo(PointOption.point(stopX, stopY));
		action.release();
		action.perform();

	}
	
}

