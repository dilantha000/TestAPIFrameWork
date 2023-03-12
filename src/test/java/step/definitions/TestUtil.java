package step.definitions;
import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.requests.TestJson;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.json.JSONObject;
import org.junit.Assert;

public class TestUtil {

    public static Response createHeaders(){
        RequestSpecification httpRequest = RestAssured.given().log().body();
        return httpRequest.get("");
    }

    public static TestJson createTestJson() {
        TestJson testJson = new TestJson();
        return testJson;
    }

    public static Response statusCode(){

        RequestSpecification request = RestAssured.given().log().body();
        request.header("Content-Type", "application/json");
        Response response = request.get();
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(responseCode);
        return statusCode();
    }

    public static Response createHeader(AuthorizationRequest authRequest) {
        RequestSpecification request = RestAssured.given().log().body();
        request.header("Content-Type", "application/json");
        Response response = request.body(authRequest).post();
        String jsonString = response.asString();
        System.out.println(jsonString);
        return response;
    }

    public static Response createPutHeader(AuthorizationRequest authRequest) {
        RequestSpecification request = RestAssured.given().log().body();
        request.header("Content-Type", "application/json");
        Response response = request.body(authRequest).put();
        String jsonString = response.asString();
        System.out.println(jsonString);
        return response;
    }


    public static void printName(){
        System.out.print("Hiiiii");
    }
}
