package restassured.pojo_seralization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class CreateFinalPayloadTest {

/*
Trying to pass paylod like below from  :https://developer.atlassian.com/server/jira/platform/jira-rest-api-examples/

{
    "fields": {
       "project":
       {
          "key": "TEST"
       },
       "summary": "REST ye merry gentlemen.",
       "description": "Creating of an issue using project keys and issue type names using the REST API",
       "issuetype": {
          "name": "Bug"
       }
   }
}

 */

    @Test
    public void simplePaylodTest() throws JsonProcessingException {

        IssueType issueType = new IssueType("Task");
        ObjectMapper objectMapper = new ObjectMapper();

        String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(issueType);
        System.out.println("Object Data is " + data);
    }


    @Test
    public void createComplexPaylod() throws JsonProcessingException {
        IssueType issueType = new IssueType("Task");
        Projects projects = new Projects("REST");

        Payload payload = new Payload("Demo summary", "Demo Description", issueType, projects);

        Fields fields = new Fields(payload);

        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields);
        System.out.println("Object Data is " + data);

    }


    @SuppressWarnings("unchecked")
    @Test
    public void passingComplexPayloadAsObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "TESTUSER");
        jsonObject.put("password", "PASSWORD");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .post("http://localhost:8085/rest/auth/1/session");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().jsonPath().prettify());

        String sessionID = response.getCookies().get("JESESSIONID");
        //steps for creating complex payload
        IssueType issueType = new IssueType("Task");
        Projects projects = new Projects("REST");
        Payload payload = new Payload("Demo summary", "Demo Description", issueType, projects);
        Fields fields = new Fields(payload);


        Response response1 = RestAssured.given().contentType(ContentType.JSON)
                .cookie("JESESSIONID", "sessionID")
                .body(fields)
                .post("http://localhost:8085/rest/api/2/issue/Task");

        System.out.println(response1.getBody().jsonPath().prettify());


    }


}