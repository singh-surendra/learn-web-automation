package getYourGuide;

import driverbase.BaseTestWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.homepage.HomePage;
import ru.yandex.qatools.allure.annotations.Title;


public class SimpleTests extends BaseTestWeb {



    HomePage homePage = new HomePage();

    @Test(groups = "SMOKE")
    @Title("Test home page loading")
    public void testHomepage() {

        homePage.openHomePage();

    }

    @Test(groups = "SMOKE")
    @Title("Test home page loading")
    public void verifyPageTitle() {
        homePage.openHomePage();
        System.out.println(homePage.getPageTitle());
    }

    @Test(groups = "SMOKE")
    @Title("Test home page loading")
    public void verifyScreenshot() {
        homePage.openHomePage();
        Assert.assertEquals("test",homePage.getPageTitle());
    }



}
