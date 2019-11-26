package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static AppiumDriverLocalService service;
    public static AppiumDriver<MobileElement> driver;



    @BeforeClass
    public AppiumDriverLocalService startServer() throws IOException {
        //

//        Runtime.getRuntime().exec("killall node");
        boolean flag = checkIfServerIsRunnning(4723);
        if (!flag) {

            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;

    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }


    public static void startEmulator() throws IOException, InterruptedException {

        String pathOfCommandFile = "/Users/surendra.singh/IdeaProjects/GeneralStore/src/main/resources/startEmulator.command";
        Runtime.getRuntime().exec(pathOfCommandFile);
        //wait for emulator to start
        Thread.sleep(10000);
    }


    @BeforeTest
    public static AppiumDriver<MobileElement> setup() {

        try {

            System.getProperty("user.dir");

            FileInputStream fis = new FileInputStream("/Users/surendra.singh/IdeaProjects/GeneralStore/src/main/java/utils/global.properties");
            Properties prop = new Properties();
            prop.load(fis);
            prop.get("GeneralStoreApp");

//           File appDir = new File("src/resources");
//            File appName = new File(appDir, "General-Store.apk");
            String apkFilePath = "/Users/surendra.singh/IdeaProjects/GeneralStore/src/main/resources/General-Store.apk";
            URL url = new URL("http://127.0.0.1:4723/wd/hub");

            DesiredCapabilities caps = new DesiredCapabilities();

            // starting emulator for every test and closing it

            //taking device name from global properties file
//           String device = (String) prop.get("deviceName");

//            String device = "SuriEmulator";

            //take device name from terminal
            String device = System.getProperty("deviceName");
            // starting emulator for every test and closing it
            if (device.contains("SuriEmulator")) {
                startEmulator();
            }

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, device);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            caps.setCapability(MobileCapabilityType.APP, apkFilePath);
            Thread.sleep(5000);
            driver = new AppiumDriver<MobileElement>(url, caps);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception exp) {

            System.out.println("Cause is :" + exp.getCause());
            System.out.println("Message is :" + exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }



    @AfterTest
    public void stopServer() {
        driver.quit();
        service = AppiumDriverLocalService.buildDefaultService();
        service.stop();

    }

    public static void getScreenshot(String testName) throws IOException
    {
        System.out.println("Capturing screenshot");
        String path = "/Users/surendra.singh/IdeaProjects/GeneralStore/src/main/java/screenshots"+testName+".png";
        File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrfile,new File(path));

    }





}