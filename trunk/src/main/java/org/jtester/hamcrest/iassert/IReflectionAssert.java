package org.jtester.hamcrest.iassert;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<T, E extends IAssert<T, ?>> extends IAssert<T, E> {

	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes);

	public E lenientEqualTo(T expected);

	public E propertyEqualTo(String property, T expected);
}
