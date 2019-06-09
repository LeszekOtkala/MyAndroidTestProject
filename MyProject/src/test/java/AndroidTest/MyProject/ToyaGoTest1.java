package AndroidTest.MyProject;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;
import pages.MainMenu;
import pages.SettingsPage;
import pages.TvPlayerPage;

public class ToyaGoTest1 {
	/*
	 *  Test sprawdza czy po zaznaczeniu checkboxa zezwalającego na odtwarzanie, odtwarzacz się uruchomi
	 *   i niewyświetlony zostanie komunikat o konieczności włączenia wifi
	 *   dla uruchomienia yestu Wifi powinno być wyłączone, a dane komórkowe włączone
	 */
	
	public static AndroidDriver driver;
	private String currentText;
	private MainMenu mainMenu;
	private SettingsPage settingsPage;
	private TvPlayerPage tvPlayerPage;
	
	@Test
	public void test1() throws InterruptedException, MalformedURLException {
		
		driver=new CreateDriver().getDriver();
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		mainMenu=new MainMenu(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		
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
		
		
		if(!settingsPage.isAllowDataCheckBoxChecked())
			settingsPage.allowDataCheckBoxClick();
		
		
		if(settingsPage.isAllowDataCheckBoxChecked()) {
			
			settingsPage.backButtonClick();
		}
		System.out.println("ToTu\n");
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
		
		try {
			Assert.assertTrue("Odtwarzacz się nie uruchomił",tvPlayerPage.isVideoDisplayed()&&tvPlayerPage.isSeekBarDisplayed());
			}
		catch(NoSuchElementException ex)
			{
				System.out.println("Nie znaleziono co najmniej jednego elementu odtwarzacza");
				Assert.assertTrue("Nie znaleziono co najmniej jednego elementu odtwarzacza",false);
			}

	}

		
	@After
	public void quitDriver(){
		if(driver!=null)
			driver.quit();
			driver=null;
	}
	
}

