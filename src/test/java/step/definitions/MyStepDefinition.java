package step.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.net.URI;

public class MyStepDefinition {
    private Scenario scenario;
    private Response response;
    private final String BASE_URL = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url");

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
        // response = req.when().get("");

    }

    @Then("Response Code {string} is validated")
    public void response_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseMessage, responseCode + "");
    }

    @Then("Response should display {string}")
    public void responseShouldDisplay(String name) {
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println("Response body is: " + response.getStatusLine());
        System.out.println(response.prettyPrint());
        Assert.assertEquals(bodyAsString.contains(name), name);


    }

    @Given("Get the Author details status code")
    public void getAuthorDetails() {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url2");
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("");
        System.out.println("Response body is: " + response.getStatusLine());
        System.out.println(response.prettyPrint());
        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode, 200);

    }

    @Given("Get the email {string}")
    public void getTheCityName(String emailID) {

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/users?page=2");
        JsonPath jsonPathEvaluator =response.jsonPath();
        String email = jsonPathEvaluator.get("email");

        System.out.println(" response is"+ email);
       Assert.assertEquals(email,emailID,emailID);
        System.out.println(response.prettyPrint());

    }

    @Then("Verify the city name")
    public void verifyTheCityName() {



    }
}
