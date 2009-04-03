package org.jtester.hamcrest.iassert.common.impl;

import org.jtester.hamcrest.iassert.common.IAssert;
import org.jtester.hamcrest.iassert.common.IReflectionAssert;
import org.jtester.hamcrest.matcher.UnitilsPropertyMatcher;
import org.jtester.hamcrest.matcher.UnitilsReflectionMatcher;
import org.unitils.reflectionassert.ReflectionComparatorMode;

public class ReflectionAssert<T, E extends IAssert<T, ?>> extends
		BaseAssert<T, E> implements IReflectionAssert<E> {

	public ReflectionAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ReflectionAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public ReflectionAssert(Class<T> clazT, Class<? extends IAssert<?, ?>> clazE) {
		super(clazT, clazE);
	}

	public E reflectionEq(Object expected, ReflectionComparatorMode... modes) {
		UnitilsReflectionMatcher matcher = new UnitilsReflectionMatcher(
				expected, modes);
		return this.assertThat(matcher);
	}

	public E lenientEq(Object expected) {
		UnitilsReflectionMatcher matcher = new UnitilsReflectionMatcher(
				expected, new ReflectionComparatorMode[] {
						ReflectionComparatorMode.IGNORE_DEFAULTS,
						ReflectionComparatorMode.LENIENT_ORDER });
		return this.assertThat(matcher);
	}

	public E propertyEq(String property, Object expected) {
		UnitilsPropertyMatcher matcher = new UnitilsPropertyMatcher(property,
				expected, null);
		return this.assertThat(matcher);
	}
}
