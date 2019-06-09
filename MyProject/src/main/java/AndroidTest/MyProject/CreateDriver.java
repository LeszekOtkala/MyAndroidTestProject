package AndroidTest.MyProject;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class CreateDriver {
	
	private static AndroidDriver driver;
	
	public CreateDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		
		capabilities.setCapability("deviceName", "3300628333e3a29b");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.toya.toyago");
		capabilities.setCapability("appActivity", "com.toya.toyago.MainActivity");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}
	
	public AndroidDriver getDriver() {
		
		return driver;
	}
	

}
