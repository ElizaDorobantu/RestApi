package utils;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseComponentTemaCurs40 {

	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Books";
		
	}
	
	
	public static Response doPostRequest(String path,String requestBody, int statusCode) {
		Response response = 
				given().
					contentType(ContentType.JSON).
					body(requestBody).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();
		return response;	
		
	}
}
