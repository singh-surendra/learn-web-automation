package driverbase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class BaseTestWeb {

    public static WebDriver driver = null;

    @BeforeTest
    public WebDriver setUpDriver() {

        try {
            System.setProperty("webdriver.chrome.driver", "/Users/surendra.singh/Documents/Learning/learn-web-automation/src/main/resources/chromedriver2");

            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } catch (Exception exp) {

            System.out.println("Cause is :" + exp.getCause());
            System.out.println("Message is :" + exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
