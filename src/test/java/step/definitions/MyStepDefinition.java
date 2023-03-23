package step.definitions;

import apiEngine.model.requests.AuthorizationRequest;
import apiEngine.model.responses.ResponsePojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.gherkin.internal.com.eclipsesource.json.ParseException;
import io.cucumber.java.After;
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
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import javax.print.attribute.standard.PrinterName;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import org.apache.log4j.Logger;


public class MyStepDefinition {

    private static final Logger LOGGER = Logger.getLogger(MyStepDefinition.class);
    private Scenario scenario;
    private Response response;

    private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
    private static String finalUrl;
    private static String token;
    private static String jsonString;
    private static String bookId;

    public MyStepDefinition() throws SQLException {
    }

    @Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
    }

    @After("@deleteAllUsers")
    public void deleteAllUsers() {
        deleteTheAllUsers();
    }

    Connection con = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306:/sql12606376");
   // private static final Logger LOGGER = Logger.getLogger(Log4J.class);
    Statement stmt=con.createStatement();
    String s ="insert into Employees values(6775,'Passed','Dilantha',)";

    //stmt.executeQuery(s);
    //con.close();


    @Given("Get Call to {string}")
    public void get_call_to_url(String url) throws Exception {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url4");
        RequestSpecification req = RestAssured.given();
        response = req.when().get(new URI(url));
        System.out.println(response.prettyPrint());
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
        Response response = TestUtil.createHeaders();

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
        RequestSpecification httpRequest = RestAssured.given().log().body();
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
    public void updateTheCityName(String username, String password) {

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url3");
        RequestSpecification httpRequest = RestAssured.given().log().body();
        JSONObject requestParams = new JSONObject();
        requestParams.put("UserName", "username");
        requestParams.put("Password", "password");
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.post("/User");
        System.out.println("The status received: " + response.statusLine());
        System.out.println(response.prettyPrint());
    }

    @Then("Verify the city name")
    public void verifyTheCityName() {
        TestUtil.printName();
    }

    @When("The Valid Instruction is updated with")
    public void updatePayDetailsInstruction(String type, DataTable dataTable) throws FileNotFoundException, ParseException {
        Map<String, String> featureData = dataTable.asMaps().get(0);
        System.out.println(featureData);
        //if (featureData.containsKey("effectiveDate")) {
    }

    @Given("I am an authorized user")
    public void iAmAnAuthorizedUser() {

        AuthorizationRequest authRequest = new AuthorizationRequest(ReadDataFromPropertiesFile.readDataFromPropertiesFile("username"),
                ReadDataFromPropertiesFile.readDataFromPropertiesFile("password"),
                ReadDataFromPropertiesFile.readDataFromPropertiesFile("name"));
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url5");
        RequestSpecification request = RestAssured.given().log().body();

        request.header("Content-Type", "application/json");

        response = request.body(authRequest).post("/Account/v1/GenerateToken");
        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
        System.out.println(response.prettyPrint());
    }

    @Given("A list of books are available")
    public void listOfBooksAreAvailable() {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url5");
        ;
        RequestSpecification request = RestAssured.given();
        response = request.get("/BookStore/v1/Books");

        jsonString = response.asString();
        List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
        Assert.assertTrue(books.size() > 0);

        bookId = books.get(0).get("isbn");
        System.out.println(response.prettyPrint());
    }

    @When("I add a book to my reading list")
    public void addBookInList() {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url5");
        ;
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
                        "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
                .post("/BookStore/v1/Books");
        System.out.println(response.prettyPrint());
    }

    @Then("The book is added")
    public void bookIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I remove a book from my reading list")
    public void removeBookFromList() {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url5");
        ;
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
                .delete("/BookStore/v1/Book");
    }

    @Then("The book is removed")
    public void bookIsRemoved() {
        Assert.assertEquals(204, response.getStatusCode());

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url5");
        ;
        RequestSpecification request = RestAssured.given();

        request.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        response = request.get("/Account/v1/User/" + USER_ID);
        Assert.assertEquals(200, response.getStatusCode());

        jsonString = response.asString();
        List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
        Assert.assertEquals(0, booksOfUser.size());
    }

    @Given("call the mock")
    public void callTheMock() {

        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url7");
        Response response = TestUtil.createHeaders();
        System.out.println("Response body is: " + response.getStatusLine());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Given("Add the user details {string} {string} {string}")
    public void addTheUserDetails(String code, String message, String name) {
        LOGGER.info("Initializing ExampleLog4j application");
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url9");
        AuthorizationRequest authRequest = new AuthorizationRequest(code, message, name);
        response = TestUtil.createHeader(authRequest);
    }

    @Then("Capture the url")
    public void captureTheUrl() {
        JsonPath jsonPathEvaluator = response.jsonPath();
        int authorId = jsonPathEvaluator.get("id");
        String newUrl = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url4");
        finalUrl = newUrl+authorId;
        RestAssured.baseURI = finalUrl;
    }

    @Then("Delete the all users")
    public void deleteTheAllUsers() {
        System.out.println("This is final url : " +finalUrl);
        RestAssured.baseURI = finalUrl;
        RequestSpecification httpRequest = (RequestSpecification) RestAssured.given()
                .header("Content-Type", "application/json");
        Response res = delete();
        System.out.println("The response code is - " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 204, 204);
    }

    @Then("update the user message {string}")
    public void updateTheUserMessage(String updated_message) {
        RestAssured.baseURI = finalUrl;
        RequestSpecification httpRequest = RestAssured.given().log().body();
        JSONObject requestParams = new JSONObject();
        requestParams.put("message", updated_message);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toString());
        Response response = httpRequest.put();
    }

    @Given("Add the user name")
    public void addTheUserName() {
        AuthorizationRequest authRequest = new AuthorizationRequest(ReadDataFromPropertiesFile.readDataFromPropertiesFile("username"),
                ReadDataFromPropertiesFile.readDataFromPropertiesFile("password"),
                ReadDataFromPropertiesFile.readDataFromPropertiesFile("name"));
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url9");
        response = TestUtil.createHeader(authRequest);
    }

    @Then("Verify the user name")
    public void verifyTheUserName() {

        TestUtil.printName();
    }

    @Given("Get the user data {string}")
    public void getTheUserData(String url) throws Exception {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url3");
        RequestSpecification httpRequest = RestAssured.given().log().body();
        response = httpRequest.when().get(new URI(url));
    }

    @Then("Verify the status code")
    public void verifyTheStatusCode() {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(200, responseCode);
        System.out.println(response);
    }

    @Then("Update the user details {string} {string} {string}")
    public void updateTheUserDetails(String code, String message, String name) {
        RestAssured.baseURI = ReadDataFromPropertiesFile.readDataFromPropertiesFile("url11");
        AuthorizationRequest authRequest = new AuthorizationRequest(code, message, name);
        response = TestUtil.createPutHeader(authRequest);
    }

    @Then("Verify the response message code {string}")
    public void verifyTheResponseMessage(String status) {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Then("Verify the updated message {string}")
    public void verifyTheUpdatedMessage(String name) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String authorName = jsonPathEvaluator.get("name");
        Assert.assertEquals(authorName, name, authorName);
    }

}