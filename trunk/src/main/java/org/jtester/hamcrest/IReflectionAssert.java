package org.jtester.hamcrest;

import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<T, E extends IAssert<T, ?>> extends IAssert<T, E> {

	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes);

	public E lenientEqualTo(T expected);

	public E propertyEqualTo(T expected, String property);
}
