package org.jtester.hamcrest.matcher.property;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jtester.utility.ReflectUtil;

public class PropertyMatcher extends BaseMatcher<Object> {
	private String property;
	private Matcher<?> matcher;

	private Object propertyValue = null;

	public PropertyMatcher(String property, Matcher<?> matcher) {
		this.property = property;
		this.matcher = matcher;
	}

	public boolean matches(Object actual) {
		this.propertyValue = ReflectUtil.getPropertyValue(actual, this.property);
		return matcher.matches(this.propertyValue);
	}

	public void describeTo(Description description) {
		description.appendText("the propery[" + this.property + "] of object must match");
		matcher.describeTo(description);
		description.appendText(",but actual value is:" + propertyValue);
	}
}
