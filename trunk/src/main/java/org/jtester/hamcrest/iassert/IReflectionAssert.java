package org.jtester.hamcrest.iassert;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<E extends IAssert<?, ?>> {

	public E reflectionEq(Object expected, ReflectionComparatorMode... modes);

	public E lenientEq(Object expected);

	public E propertyEq(String property, Object expected);
}
