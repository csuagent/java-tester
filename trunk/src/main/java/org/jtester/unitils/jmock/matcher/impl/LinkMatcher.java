package org.jtester.unitils.jmock.matcher.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jtester.unitils.jmock.matcher.ILinkMatcher;

public class LinkMatcher<T> extends BaseMatcher<T> implements ILinkMatcher<T> {
	private List<Matcher> list = new ArrayList<Matcher>();

	public void add(Matcher matcher) {
		this.list.add(matcher);
	}

	public boolean matches(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub

	}
}
