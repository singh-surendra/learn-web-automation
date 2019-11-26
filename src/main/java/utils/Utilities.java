package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class Utilities {

AppiumDriver<MobileElement> driver;

    public Utilities(AppiumDriver<MobileElement> driver){
        this.driver = driver;

    }

    public void scrollTillTextFound(String text ){

        String textTillSwipeIsRequired =  "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));";
        MobileElement listItem = driver.findElement
                (MobileBy.AndroidUIAutomator(textTillSwipeIsRequired));

    }
}
