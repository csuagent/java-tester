package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.IReflectionAssert;
import org.jtester.hamcrest.matcher.UnitilsReflectionMatcher;
import org.unitils.reflectionassert.ReflectionComparatorMode;

public class ReflectionAssert<T, E extends IAssert<T, ?>> extends
		BaseAssert<T, E> implements IReflectionAssert<T, E> {

	public ReflectionAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ReflectionAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public ReflectionAssert(Class<T> clazT, Class<? extends IAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

	public E reflectionEqualTo(T expected, ReflectionComparatorMode... modes) {
		UnitilsReflectionMatcher matcher = new UnitilsReflectionMatcher(
				expected, modes);
		return this.assertThat(matcher);
	}
}
