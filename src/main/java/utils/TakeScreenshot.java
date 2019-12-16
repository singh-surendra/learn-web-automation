package utils;

import driverbase.BaseTestWeb;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TakeScreenshot extends BaseTestWeb {


    public static void getScreenshot(String testName) throws IOException
    {
        System.out.println("Capturing screenshot");
        String path = "/Users/surendra.singh/Documents/Learning/learn-web-automation/src/main/resources/"+testName+".png";
        File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrfile,new File(path));

    }

}
