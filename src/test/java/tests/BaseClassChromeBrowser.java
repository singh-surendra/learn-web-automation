package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.net.URL;

public class BaseClassChromeBrowser {

     static AppiumDriver<MobileElement> driver;

    @BeforeTest
    public AppiumDriver<MobileElement> setup() {

        try {

            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");

//            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "SuriEmulator");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
//            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            driver = new AppiumDriver<>(url, caps);

        } catch (Exception exp) {

            System.out.println("Cause is :"+exp.getCause());
            System.out.println("Message is :"+exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }

}