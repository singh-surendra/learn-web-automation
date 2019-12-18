package driverbase;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;



import java.util.concurrent.TimeUnit;

@Slf4j
public class BaseTestWeb  {

    public static WebDriver driver = null;

    @BeforeMethod(alwaysRun = true)
    public  WebDriver setUpDriver() {

        try {

            String userDirPath = System.getProperty("user.dir");
//            System.setProperty("webdriver.chrome.driver", "/Users/surendra.singh/Documents/Learning/learn-web-automation/src/main/resources/chromedriver2");
            System.setProperty("webdriver.chrome.driver", userDirPath+"/src/main/resources/chromedriver2/");

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception exp) {

            System.out.println("Cause is :" + exp.getCause());
            System.out.println("Message is :" + exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        log.info("Closing Browser..");
        if (driver != null) {
            driver.quit();
        }

    }

}
