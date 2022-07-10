package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		if (req == null) {
			// creating logging.txt for logging details
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log)) // add request log in logging.txt
					.addFilter(ResponseLoggingFilter.logResponseTo(log)) // add response log in logging.txt
					.setContentType(ContentType.JSON).build();
		}
		return req;

	}

	// creating method to read properties file and read key value
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();

		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath : " + projectPath);

		// handling file input stream exception using throws IOException
		FileInputStream fis = new FileInputStream(projectPath + "/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
