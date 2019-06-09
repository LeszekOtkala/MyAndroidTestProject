package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	
	public static AndroidDriver driver;
	private String currentText;
	@Test
	public void test2() throws InterruptedException, MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		
		capabilities.setCapability("deviceName", "3300628333e3a29b");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.toya.toyago");
		capabilities.setCapability("appActivity", "com.toya.toyago.MainActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		MobileElement mainTextView=(MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
		int i=0;
		while(!mainTextView.getAttribute("text").equals("Ustawienia")&&i<30) {
			System.out.println(currentText=mainTextView.getAttribute("text"));
			swipeBy(700,850,900,850);
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
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
		
		i=0;
		while(!mainTextView.getAttribute("text").equals("Oglądaj TV")&&i<30) {
			System.out.println(currentText=mainTextView.getAttribute("text"));
			swipeBy(700,850,400,850);
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainTextView, "text", currentText)));
			i++;
			}
		activeMenuItem.click();
		
		String alert="";
		
			try {
				MobileElement alertTitle = (MobileElement) driver.findElementById("android:id/alertTitle");
				System.out.println(alertTitle.getAttribute("text"));
				
				MobileElement alertMessage = (MobileElement) driver.findElementById("android:id/message");
				//System.out.println("Wyśwetlono alert:\n"+alertTitle.getAttribute("text")+"\n"+alertMessage.getAttribute("text"));
				
				MobileElement alertBackButton = (MobileElement) driver.findElementById("android:id/button1");
				
				alert=alertTitle.getAttribute("text")+alertMessage.getAttribute("text");
				
				Assert.assertEquals("Alert jest niewłaściwy lub nie ma go wcale!","Brak połączenia Wi-Fi!Zmień ustawienia aplikacji lub podłącz urządzenie do sieci wifi." , alert);
				alertBackButton.click();
				
				}
			
			catch (Exception e) {
				
				//System.out.println("Nie było Alertu!");
							
					Assert.assertTrue("Nie było Alertu",alert.equals("Brak połączenia Wi-Fi!Zmień ustawienia aplikacji lub podłącz urządzenie do sieci wifi."));
			}
					
				
			

		
		
		
	}
	public void swipeBy(int startX, int startY, int stopX, int stopY) {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(startX, startY));
		action.moveTo(PointOption.point(stopX, stopY));
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

