package org.jtester.hamcrest.iassert.common;

import org.hamcrest.Matcher;

/**
 * the basic asserting matcher
 * 
 * @author darui.wudr
 * 
 * @param <T>
 * @param <E>
 */
public interface IBaseAssert<T, E extends IAssert<T, ?>> extends IAssert<T, E> {
	/**
	 * the asserted object should be equal to 'expected' parameter
	 * 
	 * @param expected
	 *            the expected parameter
	 * @return
	 */
	public E isEqualTo(T expected);

	/**
	 * the asserted object shouldn't be equal to the 'expected' parameter
	 * 
	 * @param expected
	 *            the expected parameter
	 * @return
	 */
	public E notEqualTo(T expected);

	/**
	 * the asserted object should in the expected parameters
	 * 
	 * @param values
	 *            the expected parameters
	 * @return
	 */
	public E in(T... values);

	/**
	 * the asserted object shouldn't in the expected parameters
	 * 
	 * @param values
	 *            the expected parameter
	 * @return
	 */
	public E notIn(T... values);

	/**
	 * the class of asserted object should be equals to the expected parameter
	 * 
	 * @param claz
	 *            the expected class
	 * @return
	 */
	public E type(Class<?> claz);

	/**
	 * the asserted object should be matched by the expected matcher
	 * 
	 * @param matcher
	 * @return
	 */
	public E is(Matcher<T> matcher);

	/**
	 * the asserted object shouldn't be matched by the expected matcher
	 * 
	 * @param matcher
	 * @return
	 */
	public E not(Matcher<T> matcher);

	/**
	 * the asserted object should be matched by all of the expected matchers
	 * 
	 * @param matcher1
	 * @param matcher2
	 * @param matchers
	 * @return
	 */
	public E allOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2, IAssert<?, ?>... matchers);

	/**
	 * the asserted object should be matched by all of the expected matchers
	 * 
	 * @param matchers
	 * @return
	 */
	public E allOf(Iterable<IAssert<?, ?>> matchers);

	/**
	 * the asserted object should be at least matched by one of the expected
	 * matchers
	 * 
	 * @param matcher1
	 * @param matcher2
	 * @param matchers
	 * @return
	 */
	public E anyOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2, IAssert<?, ?>... matchers);

	/**
	 * the asserted object should be at least matched by one of the expected
	 * matchers
	 * 
	 * @param matchers
	 * @return
	 */
	public E anyOf(Iterable<IAssert<?, ?>> matchers);

	/**
	 * the asserted object and the expected object must be with an object,not
	 * only their values should be equaled.
	 * 
	 * @param value
	 * @return
	 */
	public E same(T value);

	/**
	 * the asserted object can be anything
	 * 
	 * @return
	 */
	public E any();

	/**
	 * the asserted object should be null
	 * 
	 * @return
	 */
	public E isNull();

	/**
	 * the asserted object shouldn't be null
	 * 
	 * @return
	 */
	public E notNull();
}
