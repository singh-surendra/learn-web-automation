package pages.homepage;

import driverbase.BaseTestWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


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


}
