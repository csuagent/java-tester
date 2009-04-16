package org.jtester.hamcrest.iassert.common.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.jtester.hamcrest.iassert.common.IAssert;
import org.jtester.hamcrest.iassert.common.IComparableAssert;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;

@SuppressWarnings("unchecked")
public class ComparableAssert<T, E extends IAssert<T, ?>> extends BaseAssert<T, E> implements IComparableAssert<E> {

	public ComparableAssert(Class<? extends IAssert<?, ?>> clazE) {
		super(clazE);
	}

	public ComparableAssert(T value, Class<? extends IAssert<?, ?>> clazE) {
		super(value, clazE);
	}

	public E greaterEqual(Comparable min) {
		GreaterOrEqual matcher = new GreaterOrEqual(min);
		return this.assertThat(matcher);
	}

	public E greaterThan(Comparable min) {
		GreaterThan matcher = new GreaterThan(min);
		return this.assertThat(matcher);
	}

	public E lessEqual(Comparable max) {
		LessOrEqual matcher = new LessOrEqual(max);
		return this.assertThat(matcher);
	}

	public E lessThan(Comparable max) {
		LessThan matcher = new LessThan(max);
		return this.assertThat(matcher);
	}

	public E between(Comparable min, Comparable max) {
		if (min.compareTo(max) > 0) {
			throw new AssertionError(String.format("arg1[%s] must less than arg2[%s]", min, max));
		}
		GreaterOrEqual geq = new GreaterOrEqual(min);
		LessOrEqual leq = new LessOrEqual(max);
		Matcher<?> matcher = AllOf.allOf(geq, leq);
		return this.assertThat(matcher);
	}
}
