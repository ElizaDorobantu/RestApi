package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent2;
import utils.DataBuilder;

public class BaseComponent2Test extends BaseComponent2{

	@Test
	public void testCreateUser() {
		Response resp = doPost(DataBuilder.buildUser().toJSONString());
		System.out.println(resp.asPrettyString());
	}
}
