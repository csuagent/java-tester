package org.jtester.hamcrest.iassert.common;

import org.unitils.reflectionassert.ReflectionComparatorMode;

/**
 * 扩展自unitils的断言，针对对象属性进行断言
 * 
 * @author darui.wudr
 * 
 * @param <E>
 */
public interface IReflectionAssert<E extends IAssert<?, ?>> {
	/**
	 * 断言对象和期望对象在某种形式上是相同的
	 * 
	 * @param expected
	 *            期望对象
	 * @param modes
	 *            比较模式
	 * @return
	 */
	public E reflectionEq(Object expected, ReflectionComparatorMode... modes);

	public E lenientEq(Object expected);

	/**
	 * the specified property of asserted object should equal to the expected
	 * value
	 * 
	 * @param property
	 *            specified property
	 * @param expected
	 *            value expected to equal to
	 * @return
	 */
	public E propertyEq(String property, Object expected);
}
