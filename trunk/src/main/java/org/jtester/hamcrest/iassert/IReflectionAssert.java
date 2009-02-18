package org.jtester.hamcrest.iassert;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<T, E extends IAssert<?, ?>> {

	public E reflectionEq(T expected, ReflectionComparatorMode... modes);

	public E lenientEq(T expected);

	public E propertyEq(String property, Object expected);
}
