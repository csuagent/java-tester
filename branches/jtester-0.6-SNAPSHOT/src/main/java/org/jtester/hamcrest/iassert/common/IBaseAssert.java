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
	 * 被断言的对象等于期望的值
	 * 
	 * @param expected
	 *            期望值
	 * @return
	 */
	public E isEqualTo(T expected);

	/**
	 * 被断言的对象不等于期望的值
	 * 
	 * @param expected
	 *            期望值
	 * @return
	 */
	public E notEqualTo(T expected);

	/**
	 * 被断言对象可以在期望值里面找到
	 * 
	 * @param values
	 *            期望值
	 * @return
	 */
	public E in(T... values);

	/**
	 * 被断言对象不可以在期望值里面找到
	 * 
	 * @param values
	 *            期望值
	 * @return
	 */
	public E notIn(T... values);

	/**
	 * 被断言对象的类型等于期望类型
	 * 
	 * @param claz
	 *            期望类型
	 * @return
	 */
	public E type(Class<?> claz);

	/**
	 * 被断言对象符合matcher所定义的行为
	 * 
	 * @param matcher
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @return
	 */
	public E is(Matcher<T> matcher);

	/**
	 * 被断言对象不符合matcher所定义的行为
	 * 
	 * @param matcher
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @return
	 */
	public E not(Matcher<T> matcher);

	/**
	 * 被断言对象符合所有的对象行为定义
	 * 
	 * @param matcher1
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @param matcher2
	 * @param matchers
	 * @return
	 */
	public E allOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2, IAssert<?, ?>... matchers);

	/**
	 * 被断言对象符合所有的对象行为定义
	 * 
	 * @param matchers
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @return
	 */
	public E allOf(Iterable<IAssert<?, ?>> matchers);

	/**
	 * 被断言对象符合任一个对象行为定义
	 * 
	 * @param matcher1
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @param matcher2
	 * @param matchers
	 * @return
	 */
	public E anyOf(IAssert<?, ?> matcher1, IAssert<?, ?> matcher2, IAssert<?, ?>... matchers);

	/**
	 * 被断言对象符合任一个对象行为定义
	 * 
	 * @param matchers
	 *            对象行为定义，具体定义参见 org.hamcrest.Matcher
	 * @return
	 */
	public E anyOf(Iterable<IAssert<?, ?>> matchers);

	/**
	 * 被断言对象和期望值是同一个对象
	 * 
	 * @param value
	 *            期望值
	 * @return
	 */
	public E same(T value);

	/**
	 * 被断言对象可以使任意的值
	 * 
	 * @return
	 */
	public E any();

	/**
	 * 被断言对象值等于null
	 * 
	 * @return
	 */
	public E isNull();

	/**
	 * 被断言对象值不等于null
	 * 
	 * @return
	 */
	public E notNull();
}
