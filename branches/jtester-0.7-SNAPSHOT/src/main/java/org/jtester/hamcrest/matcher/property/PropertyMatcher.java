package org.jtester.hamcrest.matcher.property;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jtester.utility.ReflectUtil;

public class PropertyMatcher extends BaseMatcher<Object> {
	private String property;
	private Matcher<?> matcher;

	public PropertyMatcher(String property, Matcher<?> matcher) {
		this.property = property;
		this.matcher = matcher;
	}

	public boolean matches(Object actual) {
		Object value = ReflectUtil.getFieldValue(actual, this.property);
		return matcher.matches(value);
	}

	public void describeTo(Description description) {
		description.appendText("the propery " + this.property);
		matcher.describeTo(description);
	}
}
