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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;

	static String place_id;

	// create an object to TestDataBuild to get TestData such place data by calling
	// addPlacePayLoad method
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		req = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
		// place data coming TestDataBuild
		// class

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = req.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = req.when().get(resourceAPI.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue());

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);

	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {

		req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
