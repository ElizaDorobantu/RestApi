package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestApiExample{

	@SuppressWarnings("unchecked")
	@Test
	public void postATodoTest() {
	
		File fisier = new File("data.json");
		Faker faker =  new Faker();
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("title", faker.yoda().quote());
		requestBody.put("body", faker.chuckNorris().fact());
		
		Response respone = RestAssured
				.given()
					.header("accept", "application/json")
					.header("Content-Type", "application/json")
					//ex1 --> body as string
					//.body("{\"title\":\"TestCeva\",\"body\":\"TestCeva\"}")
					//ex2 --> body as file
					//.body(new File("data.json"))
					//.body(fisier)
					//ex 3 --> body as JsonObject
					.body(requestBody.toJSONString())
				.when()
					.post("https://keytodorestapi.herokuapp.com/api/save")
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(respone.asPrettyString());
		String info = respone.jsonPath().getString("info");
		System.out.println(info);
		assertEquals(info,"Todo saved! Nice job!");
	}
	
	
	
}