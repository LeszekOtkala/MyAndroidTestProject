package pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class SettingsPage extends CategoryPage{
	
	
	
	
	By allowDataCheckBox =  By.id("com.toya.toyago:id/allow_3g");
	

	public SettingsPage(AndroidDriver driver) {
		super(driver);
		//this.driver=driver;
		
	}
	
	public Boolean isAllowDataCheckBoxChecked() {
		return driver.findElement(allowDataCheckBox).getAttribute("checked").equals("true");
	}
	
	public void allowDataCheckBoxClick() {
		driver.findElement(allowDataCheckBox).click();
	}
}
