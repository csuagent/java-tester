package org.jtester.unitils.jmock.matcher;

import org.hamcrest.Matcher;

public interface ILinkMatcher<T> extends Matcher<T> {
	public void add(Matcher<?> matcher);
}
