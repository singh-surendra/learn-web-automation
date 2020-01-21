package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CsvReader {


    @DataProvider(name = "data-provider-csv")
    public  Object[][] getData() throws IOException, CsvException {

        Object[][] obj = new Object[2][3];
        String csvPath = "/src/main/resources/testData.csv";

        // This will load csv file
        CSVReader csvReader = new CSVReader(new FileReader(csvPath));


        // this will load content into list
        List<String[]> list = csvReader.readAll();
        System.out.println("Total rows which we have is " + list.size());

        //String objects for holding all csv values

        // create Iterator reference
        Iterator<String[]> itr = list.iterator();

        // Iterate all values
        while (itr.hasNext()) {

            String[] str = itr.next();
            System.out.print(" Values are ");
            for (int i = 0; i < str.length; i++) {
                if (i == 0) {
                    obj[0][0] = str[0];
                    System.out.print(" " + str[0]);
                } else if (i == 1) {
                    obj[0][1] = str[1];
                } else if (i == 2) {
                    obj[0][2] = str[2];
                } else if (i == 3) {
                    obj[1][0] = str[3];
                } else if (i == 4) {
                    obj[1][1] = str[4];
                } else {
                    obj[1][2] = str[5];
                }
            }
        }
        return obj;
    }



    @Test(dataProvider = "data-provider-csv")
    public void test(String data){

        System.out.println("Data is"+data);


    }




    @DataProvider(name = "test")
    public Object[][] getDataFromCSV(){

        Object[][] data = new Object[3][2];
        List<String> dataList = new ArrayList<>();
        dataList.add("Kolkata, India");  //0
        dataList.add("Dublin, Ireland"); //1
        dataList.add("12/12/2019");//2

        data[0][0] = 1;
        data[0][1] = dataList;

        dataList = new ArrayList<>();
        dataList.add("Dublin, Ireland");//3
        dataList.add("Kolkata, India");//4
        dataList.add("01/01/2020");//5

        data[1][0] = 2;
        data[1][1] = dataList;

        dataList = new ArrayList<>();
        dataList.add("Dublin, Ireland");
        dataList.add("Mumbai, India");
        dataList.add("01/05/2020");

        data[2][0] = 3;
        data[2][1] = dataList;

        return data;

    }

}



