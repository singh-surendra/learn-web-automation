package utils;

import driverbase.BaseTestWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class BrowserWait extends BaseTestWeb {


    public static FluentWait getFluentWait() {
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return fluentWait;
    }

    public   static WebElement waitUntilElementIsPresent(By by) {
      WebElement element= (WebElement) getFluentWait().until(ExpectedConditions.presenceOfElementLocated(by));
      return element;
    }




}
