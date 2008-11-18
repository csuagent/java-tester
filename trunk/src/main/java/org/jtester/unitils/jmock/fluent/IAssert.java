package org.jtester.unitils.jmock.fluent;

import org.hamcrest.Matcher;
import org.jmock.Expectations;

public interface IAssert<T, E extends IAssert<T, ?>> extends Matcher<T> {
	public E eq(T expected);

	public E notEq(T expected);

	public E in(T... values);

	public E notIn(T... values);

	public E type(Class<?> claz);

	public E is(Matcher<T> matcher);

	public E not(Matcher<T> matcher);

	public E and(IAssert matcher1, IAssert matcher2, IAssert... matchers);

	public E and(Iterable<IAssert> matchers);

	public E or(IAssert matcher1, IAssert matcher2, IAssert... matchers);

	public E or(Iterable<IAssert> matchers);

	public T match(Expectations expectations);

	public E setValue(T value);

	// public E same(T value);
}
