package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider_CSV {



    @DataProvider(name = "csvDataProvider")
    public Object[][] csvDataProvider(){
         String csvFilePath = "/Users/surendra.singh/Documents/Suri Automation Learning/REST Assured/learn-web-automation/src/test/java/resources/testData.csv";
        return ReadCsvFileUtil.readCsvFile(csvFilePath);

    }

}
