package org.jtester.hamcrest.iassert.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.hamcrest.iassert.IComparableAssert;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class ComparableAssert<T extends Comparable<T>, E extends IComparableAssert<T, ?>> extends BaseAssert<T, E>
		implements IComparableAssert<T, E> {

	public ComparableAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ComparableAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public ComparableAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E geq(T min) {
		GreaterOrEqual<T> matcher = new GreaterOrEqual<T>(min);
		return this.assertThat(matcher);
	}

	public E gt(T min) {
		GreaterThan<T> matcher = new GreaterThan<T>(min);
		return this.assertThat(matcher);
	}

	public E leq(T max) {
		LessOrEqual<T> matcher = new LessOrEqual<T>(max);
		return this.assertThat(matcher);
	}

	public E lt(T max) {
		LessThan<T> matcher = new LessThan<T>(max);
		return this.assertThat(matcher);
	}

	public E between(T min, T max) {
		if (min.compareTo(max) > 0) {
			throw new AssertionError(String.format("arg1[%s] must less than arg2[%s]", min, max));
		}
		GreaterOrEqual<T> geq = new GreaterOrEqual<T>(min);
		LessOrEqual<T> leq = new LessOrEqual<T>(max);
		Matcher<?> matcher = AllOf.allOf(geq, leq);
		return this.assertThat(matcher);
	}
}
