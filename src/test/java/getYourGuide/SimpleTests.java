package getYourGuide;

import driverbase.BaseTestWeb;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.homepage.HomePage;
import ru.yandex.qatools.allure.annotations.Title;


public class SimpleTests extends BaseTestWeb {



    HomePage homePage = new HomePage();

    @Test(groups = {"SMOKE"})
    @Title("Test home page loading")
    public void testHomepage() {

        homePage.openHomePage();

    }

    @Test(groups = {"SMOKE"})
    @Title("Test home page loading")
    public void verifyPageTitle() {
        homePage.openHomePage();
        System.out.println(homePage.getPageTitle());
    }

    @Test(groups = {"SMOKE"})
    @Title("Test home page loading")
    public void verifyScreenshots() {
        homePage.openHomePage();
        Assert.assertEquals("test",homePage.getPageTitle());
    }


    @Test
    public void test1223(){

        System.out.println(System.getProperty("os.name"));
    }


    @Test
    public void testSignUpLink() throws InterruptedException {
        homePage.clickSignUp();
    }


}
