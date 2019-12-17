package utils;

import driverbase.BaseTestWeb;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class TakeScreenshot extends BaseTestWeb {


    public static String captureScreenshot(String testName) {

        String screenshotPath = "";
        try {

            System.out.println("Capturing screenshot");
            String workingDir = System.getProperty("user.dir");

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                screenshotPath = workingDir + "\\Screenshots\\" + testName + "_" + LocalDateTime.now() + ".png";
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                screenshotPath = workingDir + "/Screenshots/" + testName + "_" + LocalDateTime.now() + ".png";
            }

            File scrfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrfile, new File(screenshotPath));

        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());

        }
        return screenshotPath;

    }
}
