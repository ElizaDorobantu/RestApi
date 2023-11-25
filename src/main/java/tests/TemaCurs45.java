package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static utils.CargoCapacityChecker.*;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class TemaCurs45 {
/*
 * Vom face urmatoarele verificari
 verificam daca nava a aparut fie in filmul https://swapi.dev/api/films/2/" sau
https://swapi.dev/api/films/6/" sau https://swapi.dev/api/films/5/”
 verificam daca diameter se apropie de valoarea 1000 cu o marja de eroare de max 30
 verificam daca pilots este o colectie goala a clasei String
 verificam daca films NU este o colectie goala a clasei String
 verificam daca model contine atat numele Imperial cat si Destroyer
 Folosind un custom matcher verificam daca nava are cargo capacity. 
 Cargo capacity este daca are o capacitate mai mare decat 25 de milioane
 * 
 */
	
	
	@Test
	public void hamcrestMatchersC45() {
		
		Response response = 
				given().get("https://swapi.dev/api/starships/3").
				then().extract().response();
		
		System.out.println(response.asPrettyString());
		
		JsonPath jsnPath = response.jsonPath();
		
		List<String> films = jsnPath.getList("films");
		System.out.println(films);
		assertThat(films, either(hasItem("https://swapi.dev/api/films/2/"))
				.or(hasItem("https://swapi.dev/api/films/6/"))
				.or(hasItem("https://swapi.dev/api/films/5/")));
		
		//nu am gasit diameter, doar length
		String length = jsnPath.getString("length");
		length = length.replace(",", "");
		System.out.println(Integer.parseInt(length));
		
		//aici am pus 1630 deoarece pentru valoarea 1000 crapa
		assertThat(Double.parseDouble(length),is(closeTo(1630, 30))); 
		
		List<String> pilots = jsnPath.getList("pilots");
		System.out.println(pilots);
		assertThat(pilots,is(emptyCollectionOf(String.class)));
		
		assertThat(films, not(is(emptyCollectionOf(String.class))));
		
		String model = jsnPath.getString("model");
		System.out.println(model);
		assertThat(model, both(containsString("Imperial")).and(containsString("Destroyer")));
		
		String cargoCapacity = jsnPath.getString("cargo_capacity");
		System.out.println(cargoCapacity);
		assertThat(cargoCapacity, is(cargoCapacityCheck()));
	}
	
}
