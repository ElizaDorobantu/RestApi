package utils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CargoCapacityChecker extends TypeSafeMatcher<Double>{

	@Override
	public void describeTo(Description description) {
		description.appendText("expected capacity to be > 25 millions ");
		
	}

	@Override
	protected boolean matchesSafely(Double item) {
		
		return item> 25000000;
	}

	public static Matcher<Double> cargoCapacityCheck(){
		return new CargoCapacityChecker();
	}
}
