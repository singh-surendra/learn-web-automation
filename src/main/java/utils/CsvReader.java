package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CsvReader {


    @DataProvider(name = "InputCSVData")
      public static Object[][] test() throws IOException, CsvException {

        Object[][] obj = new Object[2][3];
        String csvPath = "/Users/surendra.singh/Documents/Suri Automation Learning/REST Assured/learn-web-automation/src/main/resources/testData.csv";

        // This will load csv file
        CSVReader csvReader = new CSVReader(new FileReader(csvPath));

        // this will load content into list
        List<String[]> list = csvReader.readAll();
        System.out.println("Total rows which we have is " + list.size());

        // create Iterator reference
        Iterator<String[]> itr = list.iterator();

        // Iterate all values
        while (itr.hasNext()) {

            String[] str = itr.next();
            System.out.print(" Values are ");
            for (int i = 0; i < str.length; i++) {
                System.out.print(" " + str[i]);
            }
            System.out.println("   ");

            obj[0][0] = itr.next();

        }
         return obj;
    }
}



