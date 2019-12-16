package logging;



import driverbase.BaseTestWeb;

import groovy.util.logging.Slf4j;
import lombok.extern.java.Log;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;


@Log
@Slf4j
@Component
public class LombokPlugin extends BaseTestWeb {


    @Test
    public void test1(){



        log.info("Test");

        driver.get("https://github.com/mplushnikov/lombok-intellij-plugin");


    }








}
