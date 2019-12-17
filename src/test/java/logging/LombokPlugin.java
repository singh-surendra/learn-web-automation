package logging;



import driverbase.BaseTestWeb;

//import groovy.util.logging.Slf4j;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;


//@Log
@Slf4j
@Component

public class LombokPlugin extends BaseTestWeb {





    @Title("Test1")
    @Test
    public void test1(){

                log.info("Test");

        driver.get("https://github.com/mplushnikov/lombok-intellij-plugin");
//        Assert.assertEquals("test",driver.getTitle());


    }


    @Title("Test12")
    @Test
    public void test2(){

        log.info("Test");

        driver.get("https://github.com/mplushnikov/lombok-intellij-plugin");
        Assert.assertEquals("test",driver.getTitle());

    }

    @Title("Test12")
    @Test(groups = "SMOKE")
    public void test3(){

        log.info("Test");
        String path = System.getProperty("user.dir");
        System.out.println(path);

    }











}
