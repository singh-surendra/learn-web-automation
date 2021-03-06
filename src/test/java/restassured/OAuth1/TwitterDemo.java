package restassured.OAuth1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
                .oauth("CWyom5vPoU7yPDFGhzlH21Olq",
                        "OBRw7oBZLVRyw73G56AxqPDXTpzbVQqX7zpeLAQJHSeBh36J5w",
                        "1203704968986972160-n19BFWMHhy2xBiWUpmtHAHu1Yizw5J",
                        "K2W3guiYaOmslVh54EJGs3KWqZr9APPU3kGdSuh2V69vW")
                .post("https://api.twitter.com/1.1/statuses/update.json?status=This is my first tweet via API");

        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asString());
        //tweet id after succeess : 1203722115008077826

    }


    @Test
    public void postTweetAndVerify() {


        Response response = RestAssured.given()
                .auth()
                .oauth("CWyom5vPoU7yPDFGhzlH21Olq",
                        "OBRw7oBZLVRyw73G56AxqPDXTpzbVQqX7zpeLAQJHSeBh36J5w",
                        "1203704968986972160-n19BFWMHhy2xBiWUpmtHAHu1Yizw5J",
                        "K2W3guiYaOmslVh54EJGs3KWqZr9APPU3kGdSuh2V69vW")
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
                .oauth("CWyom5vPoU7yPDFGhzlH21Olq",
                        "OBRw7oBZLVRyw73G56AxqPDXTpzbVQqX7zpeLAQJHSeBh36J5w",
                        "1203704968986972160-n19BFWMHhy2xBiWUpmtHAHu1Yizw5J",
                        "K2W3guiYaOmslVh54EJGs3KWqZr9APPU3kGdSuh2V69vW")
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
                .oauth("CWyom5vPoU7yPDFGhzlH21Olq",
                        "OBRw7oBZLVRyw73G56AxqPDXTpzbVQqX7zpeLAQJHSeBh36J5w",
                        "1203704968986972160-n19BFWMHhy2xBiWUpmtHAHu1Yizw5J",
                        "K2W3guiYaOmslVh54EJGs3KWqZr9APPU3kGdSuh2V69vW")
        .post("https://api.twitter.com/1.1/statuses/destroy/"+tweetID+".json");
        System.out.println("Tweet deleted");
    }

}
