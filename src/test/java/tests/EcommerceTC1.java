package tests;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseClass;
import utils.TestData;
import utils.Utilities;

import java.io.IOException;
import java.util.Set;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class EcommerceTC1 extends BaseClass {


//    @BeforeMethod
//    public void killAllNodes() throws IOException, InterruptedException {
//
//        Runtime.getRuntime().exec("killall node");
//        //wait for emulator to start
//        Thread.sleep(6000);
//    }



    @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
    public void testWithMultipleDataSet(String input) throws IOException, InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys(input);
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        Thread.sleep(5000);
    }





    @Test
    public void testCaseForFailing() throws IOException {


        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.findElement(By.xpath("//*[@text='Female']")).click();

        String actualText = driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).getText();
        String ExpectedText = "Let's Shop here which is not present on the screen";
        assert actualText == ExpectedText;

//        MobileElement listItem = driver.findElement
//                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
//        listItem.click();
//        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    }


    @Test
    public void helloTest() throws IOException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.hideKeyboard();

        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
//        Swiping till expected text is found
        MobileElement listItem = driver.findElement
                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        listItem.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    }

    @Test
    public void verifyToastErrorMessage() {

        String expectedError = "Please enter your name";

        //By default the class name will be = "android.widget.Toast"
        //Toast error message does not have objects
        //Multiple toast error messages then android.widget.Toast[1] will represent FIRST message
        //Every toast message will have 'name' attribute which has content of the message
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name").trim();
        Assert.assertEquals(toastMessage, expectedError);


    }


    @Test
    public void validateAndAddItemToShopCart() {

        String itemName = "Jordan 6 Rings";
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        // (1)       MobileElement listItem = driver.findElement
        //                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));

        // (2)alternative way to avoid hard coded text
//        String country = "Argentina";
//        String textTillSwipeIsRequired =  "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"));";
//        MobileElement listItem = driver.findElement
//                (MobileBy.AndroidUIAutomator(textTillSwipeIsRequired));


        // (3) use swipe from utilities class

        Utilities util = new Utilities(driver);
        util.scrollTillTextFound("Argentina");

        driver.findElement(By.xpath("//*[@text='Argentina']")).click();

//        listItem.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //verticalscroll from parentList to childItem--this is required to see the item in full view
        driver.findElement(MobileBy.AndroidUIAutomator
                ("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\"))." +
                        "scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i = 0; i < count; i++) {
            String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (text.equalsIgnoreCase("Jordan 6 Rings")) {
                System.out.println("Element is found..hurrr");
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        String lastpageItemName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(itemName, lastpageItemName);

    }

    @Test
    public void validateSumOfProductsIsEqualToTotalAmount() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        MobileElement listItem = driver.findElement
                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        listItem.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // 0 is here as after clicking on first add icon, the text changes

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);
        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        double sum = 0;
        for (int i = 0; i < count; i++) {
            String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            double amount = getAmount(amount1);
            sum = sum + amount;//280.97+116.97
        }
        System.out.println(sum + "sum of products");
        String total = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double totalValue = getAmount(total);
        System.out.println(totalValue + "Total value of products");
        Assert.assertEquals(sum, totalValue);

    }

    public static double getAmount(String value) {
        value = value.substring(1);
        double amount2value = Double.parseDouble(value);
        return amount2value;
    }


    @Test
    public void tapAndLongPress() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        MobileElement listItem = driver.findElement
                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        listItem.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // 0 is here as after clicking on first add icon, the text changes
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);

        WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
        TouchAction t = new TouchAction(driver);
        t.tap(tapOptions().withElement(element(checkbox))).perform();
        WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
        t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

    }

    @Test
    public void hybridWebviewOperations() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello Test");
        driver.findElement(By.xpath("//*[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        MobileElement listItem = driver.findElement
                (MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        listItem.click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click(); // 0 is here as after clicking on first add icon, the text changes
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(7000);

        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println(contextName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("hello");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");


    }


}
