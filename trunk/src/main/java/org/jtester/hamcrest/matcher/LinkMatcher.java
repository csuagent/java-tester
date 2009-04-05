package org.jtester.hamcrest.matcher;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class LinkMatcher<T> extends BaseMatcher<T> {
	private final List<Matcher<?>> matchers = new ArrayList<Matcher<?>>();

	public void add(Matcher<?> matcher) {
		this.matchers.add(matcher);
	}

	public boolean matches(Object obj) {
		for (Matcher<?> matcher : matchers) {
			if (!matcher.matches(obj)) {
				return false;
			}
		}
		return true;
	}

	public void describeTo(Description description) {
		description.appendList("(", " and ", ")", matchers);
	}
}
