package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TemaCurs40 {
	
	@SuppressWarnings("unchecked")
	@Test
	public void postARequest( ) {
		
		File fisier = new File("dataTemaCurs40.json");
		Faker faker = new Faker();
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("id", faker.idNumber());
		requestBody.put("title", faker.book().title());
		requestBody.put("description", faker.book().publisher());
		requestBody.put("pageCount", faker.idNumber());
		requestBody.put("excerpt", faker.chuckNorris().fact());
		requestBody.put("publishDate", "2023-10-29T20:03:54.547Z");
		
		Response response = RestAssured 
				.given()
					.header("accespt","application/json")
					.header("Content-Type","image/svg+xml")
					//ex1 --> body as string
					//.body("{\"title\":\"TestCeva\",\"body\":\"TestCeva\"}")
					//ex2 -->body as file
					//.body(new File ("data.json"))
					//.body(fisier)
					//ex3 --> body as JsonObject
					.body(requestBody.toJSONString())
				.when()
					.post("https://keytodorestapi.herokuapp.com/api/save")
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(response.asPrettyString());
		
		String info = response.jsonPath().getString("info");
		System.out.println(info);
		assertEquals(info,"Todo saved! Nice job!");
	}
	
}
