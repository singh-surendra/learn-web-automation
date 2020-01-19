package restassured.cookieAuthentication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class CookieBasedAuthentication {

/*
1. Create JSONObject and store (put) user name and passsord in this
2. Pass this json object as body of request
3. String sessionID =  Response.getCookies().get("JESESSIONID");
4. Pass above seesionID in sub-sequent calls

 */


@Test
    public void createJesessionID(){


    JSONObject jsonObject =  new JSONObject();
    jsonObject.put("username", "TESTUSER");
    jsonObject.put("password", "PASSWORD");

        Response response = RestAssured.given()
            .header("Content-Type","application/json")
            .body(jsonObject)
            .post("http://localhost:8085/rest/auth/1/session");

    System.out.println(response.getStatusCode());
    System.out.println(response.getBody().jsonPath().prettify());

    String sessionID = response.getCookies().get("JESESSIONID");

    Response response1 = RestAssured.given().contentType(ContentType.JSON)
            .cookie("JESESSIONID", "sessionID")
            .body("")
            .post("http://localhost:8085/rest/api/2/issue/Task");

     System.out.println(response1.getBody().jsonPath().prettify());

}

}
