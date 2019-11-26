package utils;

import org.testng.annotations.DataProvider;

public class TestData {


    @DataProvider(name = "InputData")
    public static Object[][] getDataForEditField(){

        //2 sets of data , "Hello test" and "Another hello test !@#$%"

        Object[][] obj = new Object[][]
                {
                        {"Hello test"}, {"Another hello test !@#$%"}
                };
        return obj;

    }
}
