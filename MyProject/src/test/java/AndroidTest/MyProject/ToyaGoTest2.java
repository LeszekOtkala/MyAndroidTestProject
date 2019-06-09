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
import pages.MainMenu;
import pages.SettingsPage;
import pages.TvPlayerPage;

/*
 * Test sprawdza czy po odznaczeniu checkboxa zezwalającego na odtwarzanie wyświetlony zostanie komunikat o konieczności włączenia wifi
 * dla uruchomiena testu WiFi powinno być wyłączone, a dane komórkowe włączone
 */

public class ToyaGoTest2 {
	
	
	public static AndroidDriver driver;
	private String currentText;
	private MainMenu mainMenu;
	private SettingsPage settingsPage;
	private TvPlayerPage tvPlayerPage;
	
	@Test
	public void test2() throws InterruptedException, MalformedURLException {
		
		driver=new CreateDriver().getDriver();
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		mainMenu=new MainMenu(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,3);
		
		int i=0;
		while(!mainMenu.getMainTextViewText().equals("Ustawienia")&&i<30) {
			currentText=mainMenu.getMainTextViewText();
			mainMenu.swipeRight();
			i++;
			System.out.println(mainMenu.getMainTextViewText());
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
			}
		Assert.assertTrue("Zbyt wiele przeciągnięć, nie znaleziono pozycji Ustawienia",i<30);	
		
		mainMenu.activeMenuItemClick();
		
		settingsPage=new SettingsPage(driver);
		
	
		if(settingsPage.isAllowDataCheckBoxChecked())
			settingsPage.allowDataCheckBoxClick();
		
		if(!settingsPage.isAllowDataCheckBoxChecked()) {
			
			settingsPage.backButtonClick();
		}
		
		i=0;
		while(!mainMenu.getMainTextViewText().equals("Oglądaj TV")&&i<30) {
			
			currentText=mainMenu.getMainTextViewText();			
			mainMenu.swipeLeft();
			i++;
			System.out.println(mainMenu.getMainTextViewText());
			wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(mainMenu.getMainTextView(), "text", currentText)));
			
			}
	
		mainMenu.activeMenuItemClick();
		
		tvPlayerPage=new TvPlayerPage(driver);
		String alert="";
		
			try {
				alert=tvPlayerPage.getAlertTitle()+tvPlayerPage.getAlertMessage();
				
				Assert.assertEquals("Alert jest niewłaściwy lub nie ma go wcale!","Brak połączenia Wi-Fi!Zmień ustawienia aplikacji lub podłącz urządzenie do sieci wifi." , alert);
				tvPlayerPage.alertBackButtonClick();
				
				}
			
			catch (Exception e) {
				Assert.assertTrue("Nie było Alertu",alert.equals("Brak połączenia Wi-Fi!Zmień ustawienia aplikacji lub podłącz urządzenie do sieci wifi."));
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

