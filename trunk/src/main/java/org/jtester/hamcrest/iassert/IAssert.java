package org.jtester.hamcrest.iassert;

import org.hamcrest.Matcher;

public interface IAssert<T, E extends IAssert<T, ?>> extends Matcher<T> {
	public E isEqualTo(T expected);

	public E notEqualTo(T expected);

	public E in(T... values);

	public E notIn(T... values);

	public E type(Class<?> claz);

	public E is(Matcher<T> matcher);

	public E not(Matcher<T> matcher);

	public E allOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2,
			IAssert<?, ?>... matchers);

	public E allOf(Iterable<IAssert<?, ?>> matchers);

	public E anyOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2,
			IAssert<?, ?>... matchers);

	public E anyOf(Iterable<IAssert<?, ?>> matchers);

	public E same(T value);

	public E any();

	public E isNull();

	public E notNull();

	// public T match(Expectations expectations);
	// public T $(Expectations $);
}
