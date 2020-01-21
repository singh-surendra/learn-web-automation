package utils;
import com.jayway.jsonpath.JsonPath;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;

public class ReadCsvFileUtil {

    public static Object[][] readCsvFile(String filePath) {
        ArrayList<String[]> testdata = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                testdata.add(data);
            }
            br.close();
            fr.close();
        } catch (Exception s) {

        }
        return testdata.toArray(new Object[0][0]);
    }


    /**
     * This method provide wrapper methods to read values from Json files
     */

    public static String readConfigValue(String fileName, String configName) {
        String result = "";
        try {
            File jsonFile = new File("./src/test/resources/testData/" + fileName + ".json");
            result = JsonPath.read(jsonFile, "$.config." + configName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
