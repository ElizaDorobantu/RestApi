package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BaseComponentTest extends BaseComponent{
	String id;
	
	@Test(priority=1)
	public void postTodo() {
		Response response = doPostRequest("/api/save",DataBuilder.buildTodo().toJSONString(),200);
		id = response.jsonPath().getString("id");
		assertEquals(response.jsonPath().getString("info"),"Todo saved! Nice job!");//TestNG assert
		assertThat(response.jsonPath().getString("info"), equalTo("Todo saved! Nice job!")); //Hamcrest assert
	}
	
	@Test(priority=2)
	public void getTodo() {
		Response response = doGetRequest("/api/" ,id,200);
		System.out.println(response.asPrettyString());	
		assertThat(response.jsonPath().getString("_id"), is(id));
		assertEquals(response.jsonPath().getString("_id"), id);
	}
	
	@Test(priority=3)
	public void updateToDo() {
		Response response = doPutRequest("/api/todos/",id, DataBuilder.buildTodo().toJSONString(),201);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("Item updated"))); //Hamcrest assert

	}

}
