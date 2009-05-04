package org.jtester.hamcrest.iassert.common.intf;

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
	 *            比较模式,详见org.unitils.reflectionassert.ReflectionComparatorMode
	 * @return
	 */
	public E reflectionEq(Object expected, ReflectionComparatorMode... modes);

	/**
	 * reflectionEq指定比较模式为<br>
	 * org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS + <br>
	 * org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER<br>
	 * 的快捷方法
	 * 
	 * @param expected
	 * @return
	 */
	public E lenientEq(Object expected);

	/**
	 * 断言对象指定的属性(property)值等于期望值
	 * 
	 * @param property
	 *            对象属性名称
	 * @param expected
	 *            期望值
	 * @return
	 */
	public E propertyEq(String property, Object expected);
}
