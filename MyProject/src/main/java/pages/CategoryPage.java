package pages;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class CategoryPage {

	public AndroidDriver driver;

	By backButton= By.id("back");
	
	
	public CategoryPage(AndroidDriver driver) {
		this.driver=driver;
	}
	
	
	public void backButtonClick() {
		driver.findElement(getBackButton()).click();
	}


	public By getBackButton() {
		return backButton;
	}


}
