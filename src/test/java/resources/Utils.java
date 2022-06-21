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

		// calling getGlobalValue 
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		return req;

	}

	
	// creating method to read properties file and read key value
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("projectPath : "+projectPath);
		
		// handling file input stream exception using throws IOException 
		FileInputStream fis = new FileInputStream(
				projectPath+ "/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

}
