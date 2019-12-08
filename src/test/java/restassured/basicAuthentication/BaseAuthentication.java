package restassured.basicAuthentication;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseAuthentication {


    @BeforeClass
    public void setUp(){

        RestAssured.authentication = RestAssured.preemptive().basic("ToolsQA","TestPassword");

        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication/";



    }
}
