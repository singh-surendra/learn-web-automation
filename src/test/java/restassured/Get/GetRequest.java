package restassured.GetRequest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class GetRequest {

    @Test
    public void testResponseCode() {

        String endPoint = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
//        Response response = RestAssured.get(endPoint);
        Response response = get(endPoint);
        int code = response.getStatusCode();
        System.out.println("Status code is "+code);

        Assert.assertEquals(code, 200);

    }

    @Test
    public void testResponseBody() {

        String endPoint = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
//        Response response = RestAssured.get(endPoint);
        Response response = get(endPoint);
        String data  = response.asString();
        System.out.println("Data  is "+data);
        System.out.println("Response time is "+ response.getTime()); //by default it will be in  mili seconds
        System.out.println("Session ID  is "+ response.getSessionId());

    }

    @Test
    public void testResponseCode1() {

        String endPoint = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
        int code = get(endPoint).getStatusCode();
        System.out.println("Status code is "+code);
        Assert.assertEquals(code, 200);

    }

    @Test
    public void testResponseBody1() {

        String endPoint = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

        String data  = get(endPoint).asString();
        long time = get(endPoint).getTime();
        System.out.println("Data  is "+data);
        System.out.println("Response time is "+ time);

    }

}
