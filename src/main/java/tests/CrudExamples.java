package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class CrudExamples {

	JSONObject requestBody, updateBody;
	String id;
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com";
		
		Faker faker = new Faker();
		
		requestBody = new JSONObject();
		requestBody.put("title", faker.cat().name());
		requestBody.put("body", faker.chuckNorris().fact());
		
		updateBody = new JSONObject();
		updateBody.put("title", faker.ancient().god());
		updateBody.put("body", faker.backToTheFuture().quote());
		
	}
	
	/*
	 * CRUD
	 * C = Create = POST
	 * R = Read = GET
	 * U = Update = PUT/PATCH
	 * D = Delete = DELETE
	 * 
	 */
	
	//Create
	@Test(priority=1)
	public void postAToDoMessage() {
		Response obj = given().
			//.header("Content-Type", "application/json")
			contentType(ContentType.JSON).
			body(requestBody.toJSONString()).
		when().
			post("/api/save").
		then().
			statusCode(200).log().all().
			//extract().response().
			body("info", equalTo("Todo saved! Nice job!")).
			body("id", notNullValue()).
			//body("id", anything())
			extract().response();
		
		id = obj.jsonPath().getString("id");
		System.out.println(id);
		
	}
	
	//Read
	@Test(priority =2)
	public void getTodo() {
		Response response = given().
				get("/api/"+id).
				then().
				statusCode(200).extract().response();
		System.out.println(response.asPrettyString());
	}
	
	//Update
	@Test(priority=3)
	public void updateTodo() {
		Response response = given().
				contentType(ContentType.JSON).
				body(updateBody.toJSONString()).
				put("/api/todos/"+id).
				then().extract().response();
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority=4)
	public void getTodoAfterUpdate() {
		Response response = given().
				get("/api/"+id).
				then().
				statusCode(200).extract().response();
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority=5)
	public void deleteTodo() {
		Response response = given().
				delete("/api/delete"+id).
				then().extract().response();
		System.out.println(response.asPrettyString());
	}
}
