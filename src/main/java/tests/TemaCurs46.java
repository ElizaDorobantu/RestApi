package tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.DataBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.core.IsEqual;

public class TemaCurs46 {

	String token;
	String id;
	

	@SuppressWarnings("deprecation")
	@Test(priority=1)
	public void createToken() {
		Credentials credentials = new Credentials("admin","password123");
		
		Response result =
				given().
					contentType(ContentType.JSON).
					post("https://dev-todo.herokuapp.com/api/auth").
				then().extract().response();
		System.out.println(result.asPrettyString());		
		token = result.jsonPath().getString("token");
		System.out.println(token);
		id = result.jsonPath().getString("id");
		System.out.println(id);
		
		assertThat(token, not(isEmptyOrNullString()));
		assertThat(id, not(isEmptyOrNullString()));
	}
	
	@Test(priority=2)
	public void checkCreationOfNewItem() {
		Credentials credentials = new Credentials("admin","password123");
		Response result =
				given().
					contentType(ContentType.JSON).
					body(DataBuilder.buildTodo().toJSONString()).
					post("https://dev-todo.herokuapp.com/api/auth/save").
					then().extract().response();
		System.out.println(result.asPrettyString());
		
		assertThat(result.jsonPath().getString("msg"), is(equalTo("Sorry, you are not authorized")));
	}
	
	@Test(priority = 3)
	public void createNewItemWithToken() {
		Response result =
				given().
					contentType(ContentType.JSON).
					body(DataBuilder.buildTodo().toJSONString()).
					header("Token","token="+token).
					post("https://dev-todo.herokuapp.com/api/auth/save").
				then().extract().response();
		System.out.println(result.asPrettyString());
		id = result.jsonPath().getString("id");
		System.out.println(id);
		assertThat(id, is(not(emptyOrNullString())));
	}

	
	@Test(priority =4)
	public void deleteToDoItem() {
		Response result =
				given().
					contentType(ContentType.JSON).
					delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id).
				then().extract().response();
		System.out.println(result.asPrettyString());
		assertThat(result.jsonPath().getString("msg"), is(equalTo("Sorry, you are not authorized")));			
	}
	
	
	@Test(priority=5)
	public void deleteToDoItemWithWrongToken() {
		Response result = 
				given().
					contentType(ContentType.JSON).
					header("Token","token="+token+"1").
					delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id).
				then().extract().response();
		System.out.println(result.asPrettyString());
		
		assertThat(result.jsonPath().getString("msg"), is(equalTo("Wrong token")));
	}
	
	
	@Test(priority=6)
	public void deleteToDoItemWithCorrectToken() {
		Response result =
				given().
					contentType(ContentType.JSON).
					header("Token","token="+token).
					delete("https://dev-todo.herokuapp.com/api/auth/delete/"+id).
				then().extract().response();
		System.out.println(result.asPrettyString());
		assertThat(result.jsonPath().getString("msg"), is(equalTo("Event deleted.")));
	}
}
