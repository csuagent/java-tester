package org.jtester.hamcrest;

import org.hamcrest.Matcher;
import org.unitils.reflectionassert.ReflectionComparatorMode;

public interface IReflectionAssert<T, E extends IAssert<T, ?>> extends Matcher<T> {

	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes);
}
