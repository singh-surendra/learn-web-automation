package restassured.OAuth2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.Test;

public class Oauth2Practice {


/* run time token generation from code
app link : http://coop.apps.symfonycasts.com/
https://auth0.com/docs/protocols/oauth2

//Credentials : surendrasingh.db@gmail.com/Basa

Access Token for SurendraApp = 81de11f5639eea108f40fca676f73ca0f1e0038c
User-id = 611
Create an application
Generate token

 */


    @Test
    public void test1() {

        Response response = RestAssured.given()
                .auth()
                .oauth2("91807c9c070a292460d163acb216aaa756ea676a")
                .post("http://coop.apps.symfonycasts.com/api/611/chickens-feed");

        System.out.println("Code is" + response.getStatusCode());
        System.out.println("Body is" + response.getBody().asString());

    }

    @Test
    public void generateAccessTokenAndPass() {


        //Generete tokens
        Response response = RestAssured.given()
                .given()
                .formParam("client_id","SurendraApp")
                .formParam("client_secret","904088be144140f133b72827a70e161c")
                .formParam("grant_type","client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

//        System.out.println(response.jsonPath().prettify());

        //taking out access token from response

        String token  = response.jsonPath().get("access_token");

        //Using this token for next api call

        Response response1 = RestAssured.given()
                .auth()
                .oauth2(token)
                .post("http://coop.apps.symfonycasts.com/api/611/chickens-feed");

        Assert.assertEquals(200,response1.getStatusCode());
        System.out.println("Token is "+token);

    }



    @Test
    public void accessInvalidAPI() {


        //Generete tokens
        Response response = RestAssured.given()
                .given()
                .formParam("client_id","SurendraApp")
                .formParam("client_secret","904088be144140f133b72827a70e161c")
                .formParam("grant_type","client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");


        //taking out access token from response

        String token  = response.jsonPath().get("access_token");

        //Using this token for next api call

        Response response1 = RestAssured.given()
                .auth()
                .oauth2(token)
                .post("http://coop.apps.symfonycasts.com/api/611/ggs-collect");

        System.out.println("Response code is "+response1.getStatusCode());
        System.out.println("Response body is "+response1.jsonPath().prettify());

        Assert.assertEquals(401,response1.getStatusCode());

    }




}



