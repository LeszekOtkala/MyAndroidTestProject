package AndroidTest.MyProject;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;

public class HelloWorld {
	
	public static AndroidDriver driver;
	
	@Test
	public void sayHello() throws InterruptedException, MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities(); 
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability("deviceName", "3300628333e3a29b");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		if(driver.isDeviceLocked())
		driver.unlockDevice();
		driver.manage().timeouts().implicitlyWait(10L,  TimeUnit.SECONDS);	
		
		driver.get("https://www.wikipedia.org/");
		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		//assertTrue(true);
		Assert.assertEquals("Wikipedia",driver.getTitle());
		
		driver.close();
	
	}
}

