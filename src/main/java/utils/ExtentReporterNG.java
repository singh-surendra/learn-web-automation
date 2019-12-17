package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.*;
import org.testng.annotations.BeforeMethod;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;
    private ExtentHtmlReporter htmlReporter;
    private ExtentTest logger;


    @BeforeMethod
    public void getTestName (ITestResult result) throws IOException {
        if(result.getStatus()==ITestResult.FAILURE){

            String temp = TakeScreenshot.captureScreenshot(result.getTestName());
            logger = extent.createTest(result.getTestName());
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
    }



    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String workingDir = System.getProperty("user.dir");

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            htmlReporter = new ExtentHtmlReporter(workingDir + "\\Reports\\ExtentReportResults_"+ LocalDateTime.now()+".html");
        }
        else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            htmlReporter = new ExtentHtmlReporter(workingDir + "/Reports/ExtentReportResults_"+ LocalDateTime.now()+".html");
        }

//        htmlReporter = new ExtentHtmlReporter(workingDir+"/Reports/htmlReport2.html");
//        htmlReporter = new ExtentHtmlReporter(workingDir+"/Reports/htmlReport2.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }

        extent.flush();

    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());

                /*test.getTest(). = getTime(result.getStartMillis());
                test.getTest().endedTime = getTime(result.getEndMillis());*/

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();

                test.log(status, message);

                //      extent.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
