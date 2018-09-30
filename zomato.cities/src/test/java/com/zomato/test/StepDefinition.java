package com.zomato.test;

import java.util.List;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.zomato.cities.TestScenario;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class StepDefinition extends TestConfig {

	Scenario scenario;
	ExtentReports extent;
	ExtentTest logger;
	private RequestSpecification request;
	private Response response;
	private String qpbuild = null;
	private String count = null;

	@Before({ "@zomato" })
	public void setUp(Scenario scenario) throws NullPointerException {
		Log.startLog("StepDefinition.class");
		Log.info("Executing Scenario : " + scenario.getName());
		TestScenario ts = new TestScenario();
		ts.setscenario(scenario);
	}

	@After({ "@zomato" })
	public void tearDown(Scenario scenario) throws NullPointerException {

		Log.endLog("StepDefinition.class");
	}

	@Given("^I set the parameters as below and provide a valid API key$")
	public void i_set_the_parameters_as_below_and_provide_a_valid_API_key(DataTable table) throws Throwable {
	try {
			Map<String, String> data = table.asMaps(String.class, String.class).get(0);
			count = data.get("count");
			request = given().spec(TestConfig.requestSpecSetup()).queryParams(data).log().all();
			Log.info("Received " + data.toString() + " as input Parameters. Request URL Created");
		} catch (Exception e) {
			Log.error("Input could not be read");
			e.printStackTrace();
		}
	}
		

@Given("^I set the parameters as below and provide an invalid API key$")
public void i_set_the_parameters_as_below_and_provide_an_invalid_API_key(DataTable table) throws Throwable {
	try {
		Map<String, String> data = table.asMaps(String.class, String.class).get(0);
		count = data.get("count");
		request = given().spec(TestConfig.invalidrequestSpecSetup()).queryParams(data).log().all();
		Log.info("Received " + data.toString() + " as input Parameters. Request URL Created");
	} catch (Exception e) {
		Log.error("Input could not be read");
		e.printStackTrace();
	}
}
	

	@When("^I execute GET for cities$$")
	public void the_Cities_API_is_called() throws Throwable {

		try {
			response = request.when().get("/cities");
			Log.info("GET call executed ");

		} catch (Exception e) {
			Log.error("Request Failed");
			e.printStackTrace();
		}
	}

	@Then("^The Response Status Code should be (\\d+)$")
	public void the_Response_Status_Code_should_be(int statuscode) throws Throwable {
	try {// Write code here that turns the phrase above into concrete actions
			response.then().log().all().spec(TestConfig.responseSpecSetup());
			Log.info("Response is: " + response.body().asString());
			assertEquals(statuscode, response.getStatusCode());
		} catch (Exception e) {
			Log.error("Request Failed");
			e.printStackTrace();
		}
	}


@Then("^The Status returned in the Response should be \"([^\"]*)\"$")
public void the_Status_returned_in_the_Response_should_be(String status) throws Throwable {
	try {
			assertEquals(response.jsonPath().getString("status"), status);
			Log.info("Status returned in the Response is Success");
		} catch (Exception e) {
			Log.error("Status returned in the Response is not Success");
			e.printStackTrace();

		}

	}

	@Then("^The count of location_suggestions should match the count requested$")
	public void the_count_of_location_suggestions_should_match_the_count_requested() throws Throwable {
		try {
			List<String> locations = response.jsonPath().get("location_suggestions");
			assertTrue(Integer.parseInt(count) == locations.size());
			Log.info("count of location suggestions in the response match the count requested");
		}

		catch (Exception e) {
			Log.error("Count of location suggestions in the response did not match the count requested");
			e.printStackTrace();

		}
	}

	@Then("^The Response schema should be validated$")
	public void the_Response_schema_should_be_validated() throws Throwable {
		try {
			response.then().body(matchesJsonSchemaInClasspath("zomato.cities.response.schema.json"));
			Log.info("Response message validated against the schema");
		} catch (Exception e) {
			Log.error("Response schema does not match Expectation");
			e.printStackTrace();

		}
	}

	@Then("^The response time should be less than (\\d+) seconds$")
	public void the_response_time_should_be_less_than_seconds(int arg1) throws Throwable {
	try {
			response.then().time(lessThan(3000L));
			Log.info("Response Time is inside 3 seconds");
		} catch (Exception e) {
			Log.error("Response Time is longer than 3 Seconds");
			e.printStackTrace();

		}
	}

}
