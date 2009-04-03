package org.jtester.hamcrest.iassert.commoninterface;

import org.hamcrest.Matcher;

public interface ICommonAssert<T, E extends ICommonAssert<T, ?>> extends Matcher<T> {
	public E isEqualTo(T expected);

	public E notEqualTo(T expected);

	public E in(T... values);

	public E notIn(T... values);

	public E type(Class<?> claz);

	public E is(Matcher<T> matcher);

	public E not(Matcher<T> matcher);

	public E allOf(ICommonAssert<?, ?> matcher1, ICommonAssert<?, ?> matcher2,
			ICommonAssert<?, ?>... matchers);

	public E allOf(Iterable<ICommonAssert<?, ?>> matchers);

	public E anyOf(ICommonAssert<?, ?> matcher1, ICommonAssert<?, ?> matcher2,
			ICommonAssert<?, ?>... matchers);

	public E anyOf(Iterable<ICommonAssert<?, ?>> matchers);

	public E same(T value);

	public E any();

	public E isNull();

	public E notNull();
}
