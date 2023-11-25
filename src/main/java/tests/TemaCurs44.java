package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import org.hamcrest.core.IsEqual;
import org.testng.annotations.Test;

public class TemaCurs44 {

	@Test
	public void hamcrestMatchers() {
		
		Response response = 
				given(). 
					get("https://swapi.dev/api/people/1/").
				then().
					extract().response();
		
		System.out.println(response.asPrettyString());
		
		JsonPath jsnPath = response.jsonPath();
		
		String name = jsnPath.getString("name");
		System.out.println(name);
		assertThat(name,is(equalTo("Luke Skywalker")));
		
		String height = jsnPath.getString("height");
		System.out.println(height);
		assertThat(Integer.parseInt(height),is(greaterThan(171)));
		
		String mass = jsnPath.getString("mass");
		System.out.println(mass);
		assertThat(Integer.parseInt(mass), is(lessThan(80)));
		
		String hair = jsnPath.getString("hair_color");
		System.out.println(hair);
		String skin = jsnPath.getString("skin_color");
		System.out.println(skin);
		String eye = jsnPath.getString("eye_color");
		System.out.println(eye);
		String[] trasaturi = {skin,eye,hair};
		assertThat(trasaturi,is(arrayContaining("fair", "blue", "blond")));
		
		String birth_year = jsnPath.getString("birth_year");
		System.out.println(birth_year);
		assertThat(birth_year, is(not(instanceOf(Integer.class))));
		
		List<String> species = jsnPath.getList("species");
		System.out.println(species);
		assertThat(species, is(emptyCollectionOf(String.class)));
	
		List<String> vehicles = jsnPath.getList("vehicles");
		System.out.println(vehicles);
		System.out.println(vehicles.size());
		
		List<String> starships = jsnPath.getList("starships");
		System.out.println(starships);
		System.out.println(starships.size());
	
		assertThat(vehicles, hasSize(starships.size()));
		
		assertThat(starships,not(is(equalTo(vehicles))));
		
		
		
	}
}
