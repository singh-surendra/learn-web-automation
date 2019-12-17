package pages.homepage;

import driverbase.BaseTestWeb;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import utils.BrowserWait;
import static utils.BrowserWait.*;


@Slf4j
@Component
public class HomePage extends BaseTestWeb {

    public void openHomePage() {

        log.info("Opening home page");
        driver.get("https://www.getyourguide.co.uk/");

    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickSignUp() throws InterruptedException {
        driver.get("https://www.getyourguide.co.uk/");
        waitUntilElementIsPresent(By.xpath("//*[contains(text(),'Sign up')]"));
        driver.findElement(By.xpath("//*[contains(text(),'Sign up')]")).click();
        Thread.sleep(3000);
        System.out.println("Success");

    }

}
