package tests;

import driverbase.BaseTestWeb;
import org.testng.annotations.Test;

public class ExcelTest extends BaseTestWeb {

    @Test
    public void test (){
        System.out.println("Inside Test");
        driver.get("https://selenium.dev/documentation/en/getting_started/quick/#webdriver");
    }


}
