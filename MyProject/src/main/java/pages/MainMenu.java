package pages;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class MainMenu {
	
	public AndroidDriver driver;
	
	By mainTextView=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
	By activeMenuItem=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.support.v4.view.ViewPager/android.widget.FrameLayout[4]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ImageView");
	
	public MainMenu(AndroidDriver driver) {
		this.driver=driver;
	}
	
	public void swipeLeft() {
		TouchAction action = new TouchAction(driver);
		action.press(PointOption.point(900, 850));
		action.moveTo(PointOption.point(700, 850));
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
	public String getMainTextViewText() {
		
		return driver.findElement(mainTextView).getAttribute("text");
		//return mainTextView.getAttribute("text");
	}
	
	public MobileElement getMainTextView() {
		return (MobileElement)driver.findElement(mainTextView);
	}
	
	public void activeMenuItemClick() {
		driver.findElement(activeMenuItem).click(); 
	}
	
	public MobileElement getActiveMenuItem() {
		return (MobileElement) driver.findElement(activeMenuItem); 
	}


}
