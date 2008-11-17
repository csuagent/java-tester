package org.jtester.unitils.jmock.fluent;

import org.hamcrest.Matcher;
import org.jtester.unitils.jmock.fluent.impl.Assert;

public interface IAssert<T, E extends IAssert<T, E>> {
	public E or();

	public E not();

	public E eq(T expected);

	public E type(Class<?> claz);

	public E is(Matcher<T> matcher);

	public E and(Matcher<?>... matchers);

	public E and(Iterable<Matcher<?>> matchers);

	public E or(Matcher<?>... matchers);

	public E or(Iterable<Matcher<?>> matchers);

	public E in(T... values);

	public E same(T value);

	public Assert<T, E> matcher();

	// public T match(Expectations expectations);
}
