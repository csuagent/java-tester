package org.jtester.hamcrest.iassert.commoninterface;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<E extends ICommonAssert<?, ?>> {

	public E reflectionEq(Object expected, ReflectionComparatorMode... modes);

	public E lenientEq(Object expected);

	public E propertyEq(String property, Object expected);
}
