package org.jtester.hamcrest.iassert.common;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<E extends IAssert<?, ?>> {

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
