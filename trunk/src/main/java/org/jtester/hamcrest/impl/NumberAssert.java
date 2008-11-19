package org.jtester.hamcrest.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.INumberAssert;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

public class NumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, E>> extends BaseAssert<T, E>
		implements INumberAssert<T, E> {

	public NumberAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public NumberAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public NumberAssert(T value, Class<? extends IAssert> clazE) {
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
		assert min.compareTo(max) < 0;
		GreaterOrEqual<T> geq = new GreaterOrEqual<T>(min);
		LessOrEqual<T> leq = new LessOrEqual<T>(max);
		Matcher<?> matcher = AllOf.allOf(geq, leq);
		return this.assertThat(matcher);
	}
}
