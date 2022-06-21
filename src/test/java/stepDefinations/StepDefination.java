package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;

	// create an object to TestDataBuild to get TestData such place data by calling
	// addPlacePayLoad method
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		req = given().spec(requestSpecification()).body(data.addPlacePayLoad()); // place data coming TestDataBuild
																					// class

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String string2) {
		response = req.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue());

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		JsonPath js = new JsonPath(response.asString());

		assertEquals(js.get(keyValue).toString(), expectedValue);
	}

}
