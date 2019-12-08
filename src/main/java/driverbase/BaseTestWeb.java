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
            System.setProperty("webdriver.chrome.driver", "/Users/surendra.singh/IdeaProjects/learn-web-autoamtion/src/main/resources/chromedriver");
            driver = new ChromeDriver();
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
