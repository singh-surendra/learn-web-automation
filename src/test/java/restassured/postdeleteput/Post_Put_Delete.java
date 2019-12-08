package restassured.postdeleteput;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Post_Put_Delete {


    /* learning resources :https://github.com/typicode/json-server

    1. npm install -g json-server    (Node.js should be installed)

    2. json-server --watch db.json
    3.now open http://localhost:3000/posts
    4. http://localhost:3000/comments
    5. http://localhost:3000/profile

    */



@Test

    public void postExample(){

    RequestSpecification request = RestAssured.given();

    request.header("Content-Type","application/json");

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id","1001");
    jsonObject.put("title","new ttle");
    jsonObject.put("author","Surendra Singh");

    request.body(jsonObject.toJSONString());

    Response  response = request.post("http://localhost:3000/posts/");
    int code = response.getStatusCode();

    Assert.assertEquals(code, 201);

}


    @Test

    public void deleteExample(){

        RequestSpecification request = RestAssured.given();

        Response  response = request.delete("http://localhost:3000/posts/19");
        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);

    }


    @Test

    public void putExample(){

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type","application/json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","1001");
        jsonObject.put("title","new title");
        jsonObject.put("author","Surendra Singh Updated record after put");

        request.body(jsonObject.toJSONString());

        Response  response = request.put("http://localhost:3000/posts/1001");
        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);

    }






}
