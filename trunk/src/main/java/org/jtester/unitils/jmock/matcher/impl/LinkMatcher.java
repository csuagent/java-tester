package org.jtester.unitils.jmock.matcher.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jtester.unitils.jmock.matcher.ILinkMatcher;

public class LinkMatcher<T> extends BaseMatcher<T> implements ILinkMatcher<T> {
	private List<Matcher<?>> list = new ArrayList<Matcher<?>>();

	private Matcher<?> matcher;

	public void add(Matcher matcher) {
		this.list.add(matcher);
	}

	public boolean matches(Object obj) {
		this.matcher = AllOf.allOf(list);

		return this.matcher.matches(obj);
	}

	public void describeTo(Description description) {
		this.matcher.describeTo(description);
	}
}
