package org.jtester.unitils.jmock.fluent.impl;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.jtester.unitils.jmock.fluent.IAssert;

public abstract class BaseImplExpected<T, E extends IAssert<T, E>> extends Assert<T, E> implements IAssert<T, E> {

	@SuppressWarnings("unchecked")
	public BaseImplExpected(Class<T> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public E or() {
		if (this.getPrevNodeType() != MatchNodeType.Normal) {
			throw new RuntimeException("expected expression error:no prev condiction can be or");
		}
		E rightExpected = this.instance();
		this.setOrExpected(rightExpected.matcher());
		rightExpected.matcher().setPrevNodeType(MatchNodeType.Or);

		return rightExpected;
	}

	public E not() {

		E notExpected = this.instance();

		IsNot<T> matcher = new IsNot<T>(notExpected.matcher());
		notExpected.matcher().setNotExpected(this);
		notExpected.matcher().setRecordDescriptionStatus(true);
		notExpected.matcher().setPrevNodeType(MatchNodeType.Not);

		this.addMatcher(matcher);

		return notExpected;
	}

	public E eq(T expected) {
		IsEqual<T> matcher = new IsEqual<T>(expected);
		return this.link(matcher);
	}

	public E type(Class<?> expected) {
		Matcher<Object> matcher = Is.is(expected);
		return this.link(matcher);
	}

	public E is(Matcher<T> matcher) {
		Matcher<T> _matcher = Is.is(matcher);
		return this.link(_matcher);
	}

	public E and(Matcher<?>... matchers) {
		Matcher<?> matcher = AllOf.allOf(matchers);
		return this.link(matcher);
	}

	public E and(Iterable<Matcher<?>> matchers) {
		Matcher<?> matcher = AllOf.allOf(matchers);
		return this.link(matcher);
	}

	public E or(Matcher<?>... matchers) {
		Matcher<?> matcher = AnyOf.anyOf(matchers);
		return this.link(matcher);
	}

	public E or(Iterable<Matcher<?>> matchers) {
		Matcher<?> matcher = AnyOf.anyOf(matchers);
		return this.link(matcher);
	}

	public E in(T... values) {
		Matcher<T> matcher = IsIn.isOneOf(values);
		return this.link(matcher);
	}

//	public E same(T value) {
//		Same matcher = new Same(value);
//		return this.link(matcher);
//	}
}
