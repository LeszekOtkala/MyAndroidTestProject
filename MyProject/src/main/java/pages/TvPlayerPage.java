package pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class TvPlayerPage {
	public AndroidDriver driver;
	
	By video= By.id("com.toya.toyago:id/video");
	By seekBar= By.id("com.toya.toyago:id/playerSeekBar");
	By alertTitle=By.id("android:id/alertTitle");
	By alertMessage=By.id("android:id/message");
	By alertBackButton=By.id("android:id/button1");
	By tvPlayerBackButton=By.id("com.toya.toyago:id/playerBackButton");
	
	public TvPlayerPage(AndroidDriver driver) {
		this.driver=driver;
	}
	
	
	
	public String getAlertTitle() {
		return driver.findElement(alertTitle).getAttribute("text");
	}
	
	public String getAlertMessage() {
		return driver.findElement(alertMessage).getAttribute("text");
	}
	
	public void alertBackButtonClick() {
		driver.findElement(alertBackButton).click();
	}
	
	public Boolean isVideoDisplayed() {
		return driver.findElement(video).isDisplayed();
	}
	public Boolean isSeekBarDisplayed() {
		return driver.findElement(seekBar).isDisplayed();
	}
	
	public void tvPlayerBackButtonClick() {
		driver.findElement(getTvPlayerBackButton()).click();
	}


	public By getTvPlayerBackButton() {
		return tvPlayerBackButton;
	}


}
