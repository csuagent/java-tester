package org.jtester.unitils.jmock.fluent.impl;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.jtester.unitils.jmock.fluent.IAssert;

public abstract class BaseAssert<T, E extends IAssert<T, ?>> extends Assert<T, E> implements IAssert<T, E> {

	@SuppressWarnings("unchecked")
	public BaseAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	@SuppressWarnings("unchecked")
	public BaseAssert(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public E eq(T expected) {
		Matcher<T> matcher = IsEqual.equalTo(expected);
		return this.assertThat(matcher);
	}

	public E notEq(T expected) {
		Matcher<T> matcher = IsNot.not(IsEqual.equalTo(expected));
		return this.assertThat(matcher);
	}

	public E type(Class<?> expected) {
		Matcher<?> matcher = Is.is(expected);
		return this.assertThat(matcher);
	}

	public E is(Matcher<T> matcher) {
		Matcher<T> _matcher = Is.is(matcher);
		return this.assertThat(_matcher);
	}

	public E not(Matcher<T> matcher) {
		Matcher<T> _matcher = IsNot.not(matcher);
		return this.assertThat(_matcher);
	}

	public E and(Matcher<?>... matchers) {
		Matcher<?> matcher = AllOf.allOf(matchers);
		return this.assertThat(matcher);
	}

	public E and(Iterable<Matcher<?>> matchers) {
		Matcher<?> matcher = AllOf.allOf(matchers);
		return this.assertThat(matcher);
	}

	public E or(Matcher<?>... matchers) {
		Matcher<?> matcher = AnyOf.anyOf(matchers);
		return this.assertThat(matcher);
	}

	public E or(Iterable<Matcher<?>> matchers) {
		Matcher<?> matcher = AnyOf.anyOf(matchers);
		return this.assertThat(matcher);
	}

	public E in(T... values) {
		Matcher<T> matcher = IsIn.isOneOf(values);
		return this.assertThat(matcher);
	}

	public E notIn(T... values) {
		Matcher<T> _matcher = IsNot.not(IsIn.isOneOf(values));
		return this.assertThat(_matcher);
	}
}
