package getYourGuide;

import driverbase.BaseTestWeb;
import org.testng.annotations.Test;
import utils.TestDataProvider_CSV;

public class DataProviderTests extends BaseTestWeb {



    @Test( groups = {"SMOKE"},dataProvider = "csvDataProvider", dataProviderClass = TestDataProvider_CSV.class, alwaysRun = true)
    public void testMultipleSearch(String data1, String data2, String data3){

        System.out.println("Data are"+data1+data2+data3);
        System.out.println("Thread count in method 1 is"+Thread.currentThread().getId());
    }

    @Test(groups = {"SMOKE"})
    public void test(){

        System.out.println("Test");
        System.out.println("Thread count in method 2 is"+Thread.currentThread().getId());
    }

}
