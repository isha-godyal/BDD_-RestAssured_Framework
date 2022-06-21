package stepDefinations;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class StepDefination {

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;

	@Given("Add Place Payload")
	public void add_place_payload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Create place object and set value which we can pass in request body of
		// addPlace call
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setPhone_number("(+91) 983 893 3937");
		place.setWebsite("https://rahulshettyacademy.com");
		place.setName("Frontline house");

		// create list to store types
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		// set types in place object using setTypes method
		place.setTypes(myList);

		// Created nested object location and set lat and long
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);

		// set location value in place using setLocation method
		place.setLocation(loc);

		// Create request speccs or rule for any request which use the specs/rule
		req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();

		// Create response speccs or rule for any response which check for this specs/rule
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		req = given().spec(req).body(place);

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
