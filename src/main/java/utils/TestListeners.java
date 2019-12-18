package utils;


import java.io.IOException;
import java.time.LocalDateTime;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;



public class TestListeners extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;


    public void onStart(ITestContext testContext)
    {
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/myReport.html");//specify location of the report

        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);

    }


    public void onTestSuccess(ITestResult tr)
    {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.PASS,"Test Passed");
        logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
    }


    public void onTestFailure(ITestResult tr)
    {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.FAIL,"Test failed");
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

        String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";
        String screenshotPath1="/Screenshots/"+tr.getName()+".png";

//        screenshotPath = workingDir + "/Screenshots/" + testName + "_" + LocalDateTime.now() + ".png";

        String path = TakeScreenshot.captureScreenshot(tr.getName());
        System.out.println("path from screenshot is"+path);

        try {
            logger.fail("Screenshot" + logger.addScreenCaptureFromPath(screenshotPath1));

            logger.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());
            logger.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath1).build());
            logger.log(Status.FAIL,"Screenshots are also available at"+path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult tr)
    {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext)
    {
        extent.flush();

    }


}






