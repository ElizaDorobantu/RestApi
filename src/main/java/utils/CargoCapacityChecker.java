package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacityChecker extends TypeSafeMatcher<String>{

	@Override
	public void describeTo(Description description) {
		description.appendText("expected capacity to be > 25 millions ");
		
	}

	@Override
	protected boolean matchesSafely(String item) {
		
		return Double.parseDouble(item)> 25000000;
	}

	public static Matcher<String> cargoCapacityCheck(){
		return new NumberChecker();
	}
}
