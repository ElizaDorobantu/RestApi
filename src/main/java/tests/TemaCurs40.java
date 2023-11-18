package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utils.BaseComponentTemaCurs40;

public class TemaCurs40 extends BaseComponentTemaCurs40{
	
	
@Test	
public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		
		
		JSONParser parser =  new JSONParser();
		JSONArray todoList = (JSONArray) parser.parse(new FileReader("postRequestTemaCurs40.json"));;
		
		for(Object todo : todoList) {
			JSONObject objTodo = (JSONObject)todo;
			Response response = doPostRequest("/api/v1/Books", objTodo.toJSONString(), 200);
			System.out.println(response.asPrettyString());
			Assert.assertEquals(response.jsonPath().getString("id"), 107);
			
		}
			
	}	
}
