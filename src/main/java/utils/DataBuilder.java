package utils;

import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.groovy.ast.AstToTextHelper;
import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder{
	
	
	
	public static JSONObject buildTodo(){
		
		JSONObject todoBuilder =  new JSONObject();
		Faker faker =  new Faker();
		todoBuilder.put("title", faker.cat().name());
		todoBuilder.put("body", faker.chuckNorris().fact());
		
		
		try(FileWriter file =  new FileWriter("todo.json")){
			file.write(todoBuilder.toJSONString());			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return todoBuilder;
	}
	
	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		JSONObject user = new JSONObject();
		Faker faker =  new Faker();
		
		user.put("name", faker.name().firstName());
		user.put("email", faker.internet().safeEmailAddress());
		user.put("age", faker.number().numberBetween(5, 130));
		user.put("gender", "f");
		
		return user;
		
	}
	
	public static JSONObject buildToken() {
		JSONObject token = new JSONObject();
		token.put("username", "admin");
		token.put("password", "password123");
		return token;
	}

	public static JSONObject buildBooking() {
		JSONObject booking = new JSONObject();
		booking.put("firstname", "Jim");
		booking.put("lastname", "Brown");
		booking.put("totalprice", 111);
		booking.put("depositpaid", true);
			JSONObject bookingDates = new JSONObject();
			bookingDates.put("checkin", "2018-01-01");
			bookingDates.put("checkout", "2019-01-01");
		booking.put("bookingdates", bookingDates);
		booking.put("additionalneeds", "Breakfast");
		
		return booking;
	}
	
}