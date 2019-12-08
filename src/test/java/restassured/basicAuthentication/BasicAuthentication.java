package restassured.basicAuthentication;

import io.restassured.RestAssured;
import junit.framework.Assert;
import org.testng.annotations.Test;

public class BasicAuthentication extends BaseAuthentication{


    @Test
    public void basicAuthentication() {
        int code = RestAssured.given()
                .auth().preemptive()
                .basic("ToolsQA", "TestPassword")
                .when()
                .get("http://restapi.demoqa.com/authentication/CheckForAuthentication/")
                .getStatusCode();

        System.out.println("Response code from server is " + code);

        Assert.assertEquals(code, 200);

    }



    @Test
    public void basicAuthentication1() {

        //Here we setup username and passord , URI and many other constants in base class so we do nto need to provide here and all our test cases can be run without passing authentication details one by one
        int code = RestAssured.given()
                .get()
                .getStatusCode();

        System.out.println("Response code from server is " + code);
        Assert.assertEquals(code, 200);

    }


}
