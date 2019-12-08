package restassured.OAuth1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TwitterDemo {

    /*

    OAuth1- open standard for authorization
    Do not have give passwords

    Steps : 1 Rest assured
    2. Scribejava-apis
    3.Developer account
    4. Create an app in twitter developer account
    5. use Consumer API keys, Access token & access token secret while sending request



     */


    @Test
    public void postTweet() {


        Response response = RestAssured.given()
                .auth()
                .oauth("66B4MFUlA4TDKgHLdxaXQ9wcB",
                        "1odJN1JoIocEHbJenfdN8e560UVRKC1XHLcNALwixRVttGeUnQ",
                        "1203704968986972160-1aEVjtWrMw0f6diryf1rybeu1BcfWM",
                        "WE84nRvfwHyYJbye0UTLqBHyzYllauEZ60Q8Ie9xwIupV")
                .post("https://api.twitter.com/1.1/statuses/update.json?status=This is my first tweet via API");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        //tweet id after succeess : 1203722115008077826

    }


    @Test
    public void postTweetAndVerify() {


        Response response = RestAssured.given()
                .auth()
                .oauth("66B4MFUlA4TDKgHLdxaXQ9wcB",
                        "1odJN1JoIocEHbJenfdN8e560UVRKC1XHLcNALwixRVttGeUnQ",
                        "1203704968986972160-1aEVjtWrMw0f6diryf1rybeu1BcfWM",
                        "WE84nRvfwHyYJbye0UTLqBHyzYllauEZ60Q8Ie9xwIupV")
                .post("https://api.twitter.com/1.1/statuses/update.json?status=This is second  tweet via API");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().jsonPath().prettify());

        JsonPath json = response.jsonPath();
        String tweetID = json.get("id_str");
        System.out.println("My Tweet ID is "+ tweetID);


    }

    @Test
    public void postTweetAndDeleteTweet() {


        //Posting a tweet
        Response response = RestAssured.given()
                .auth()
                .oauth("SRuUcC8hwyTTcyAUkGrnB8MY0",
                        "Q6sHHHsp8i2Yv33WXXntCKxRXkvjP5v19jufPHnjbXr3l7qo3J",
                        "1203704968986972160-0JqYVKeN0xyRkRcd3B7Fk8LDyfc29k",
                        "0rPKdMUmit39wAXCfCjHwdXC6dZ2F0nuYQrD1vOjD510d")
                .post("https://api.twitter.com/1.1/statuses/update.json?status=This is my ts weet via API and it will be deleted soon");

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().jsonPath().prettify());

        JsonPath json = response.jsonPath();
        String tweetID = json.get("id_str");
        System.out.println("My Tweet ID is "+ tweetID);

        //deleting  a tweet
        Response response1 = RestAssured.given()
                .auth()
                .oauth("SRuUcC8hwyTTcyAUkGrnB8MY0",
                        "Q6sHHHsp8i2Yv33WXXntCKxRXkvjP5v19jufPHnjbXr3l7qo3J",
                        "1203704968986972160-0JqYVKeN0xyRkRcd3B7Fk8LDyfc29k",
                        "0rPKdMUmit39wAXCfCjHwdXC6dZ2F0nuYQrD1vOjD510d")
        .post("https://api.twitter.com/1.1/statuses/destroy/"+tweetID+".json");
        System.out.println("Tweet deleted");
    }

}
