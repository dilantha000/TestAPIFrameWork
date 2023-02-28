package step.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.gherkin.internal.com.eclipsesource.json.ParseException;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.Map;

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
        //Assert.assertEquals(bodyAsString.contains("name"),name);
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

    @And("Get the email {string} and {string} and {string}")
    public void getTheCityName(String author, String url, String title) {

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url3");
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get(url);
        JsonPath jsonPathEvaluator = response.jsonPath();

        String authorname = jsonPathEvaluator.get("author");
        String titlej = jsonPathEvaluator.get("title");

        System.out.println(" response is" + authorname);
        Assert.assertEquals(authorname, author, authorname);
        Assert.assertEquals(titlej, title, titlej);
        System.out.println(response.prettyPrint());
    }

    @Given("Update the email {string} and {string}")
    public void updateTheCityName(String username,String password) {

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url3");
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("UserName","username" );
        requestParams.put("Password","password");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/User");
        System.out.println("The status received: " + response.statusLine());
        System.out.println(response.prettyPrint());
    }

    @Then("Verify the city name")
    public void verifyTheCityName() {
    }


    @When("The Valid Instruction is updated with")
    public void updatePayDetailsInstruction(String type, DataTable dataTable) throws FileNotFoundException, ParseException {
        Map<String, String> featureData = dataTable.asMaps().get(0);
        System.out.println(featureData);
        //if (featureData.containsKey("effectiveDate")) {
    }

}