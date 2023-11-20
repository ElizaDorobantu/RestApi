package tests;

import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.BaseComponentTemaCurs41;
import utils.DataBuilder;

public class BaseComponentTestTemaCurs41 extends BaseComponentTemaCurs41{
	String id;
	
	@Test(priority=1)
	public void postTodo() {
		Response response = doPostRequest("/api/users", DataBuilder.buildUser().toJSONString(), 201);
		id = response.jsonPath().getString("result._id");
		System.out.println(id);
		System.out.println(response.jsonPath().getString("msg"));
		assertEquals(response.jsonPath().getString("msg"), "Successfully added!");//Testng assert
		assertThat(response.jsonPath().getString("msg"), is(equalTo("Successfully added!")));//Hamcrest assert
		
	}
	
	@Test(priority=2)
	public void getTodo() {
		Response response = doGetRequest("/api/users/", id, 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("result._id"), is(id) );//Hamcrest
		assertEquals(response.jsonPath().getString("result._id"), id);//Testng assert

	}
	
	@Test(priority=3)
	public void updateTodo() {
		Response response = doPutRequest("/api/users/", id, DataBuilder.buildUser().toJSONString(), 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("Successfully updated!")));//Hamcrest assert
	}
	
	@Test(priority=4)
	public void deleteTodo() {
		Response response = doDeleteRequest("/api/users/", id, 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("It has been deleted.")));
		assertEquals(response.jsonPath().getString("msg"),"It has been deleted.");
	}
	

}