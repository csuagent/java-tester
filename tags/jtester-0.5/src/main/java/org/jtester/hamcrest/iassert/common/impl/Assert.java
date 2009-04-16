package org.jtester.hamcrest.iassert.common.impl;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.jmock.Expectations;
import org.jtester.hamcrest.iassert.common.IAssert;
import org.jtester.hamcrest.matcher.LinkMatcher;
import org.jtester.jmock.ExpectationsUtil;
import org.jtester.utility.PrimitiveConvertor;

public abstract class Assert<T, E extends IAssert<T, ?>> extends BaseMatcher<T> implements IAssert<T, E> {

	protected Class<?> valueClaz = null;

	protected Object value;

	protected AssertType type;

	@SuppressWarnings("unchecked")
	protected Class<? extends IAssert> assertClaz;

	protected LinkMatcher<T> link;

	public Assert(Class<? extends IAssert<?, ?>> clazE) {
		this.value = null;
		this.type = AssertType.Expectations;
		this.link = new LinkMatcher<T>();
		this.assertClaz = clazE;
	}

	public Assert(T value, Class<? extends IAssert<?, ?>> clazE) {
		this.type = AssertType.AssertThat;
		this.value = value;
		this.assertClaz = clazE;
	}

	@SuppressWarnings("unchecked")
	public Assert(Class<T> clazT, Class<? extends IAssert> clazE) {
		this.type = AssertType.Expectations;
		this.valueClaz = clazT;
		this.assertClaz = clazE;
		this.link = new LinkMatcher<T>();
	}

	public void describeTo(Description description) {
		link.describeTo(description);
	}

	@SuppressWarnings("unchecked")
	public E assertThat(Matcher matcher) {
		if (this.type == AssertType.AssertThat) {
			MatcherAssert.assertThat(this.value, matcher);
		} else {
			this.link.add(matcher);
		}
		return (E) this;
	}

	@SuppressWarnings("unchecked")
	public E assertThat(Matcher matcher, String message) {
		if (this.type == AssertType.AssertThat) {
			MatcherAssert.assertThat(message, this.value, matcher);
		} else {
			this.link.add(matcher);
		}
		return (E) this;
	}

	public boolean matches(Object item) {
		return this.link.matches(item);
	}

	protected static enum AssertType {
		AssertThat, Expectations;
	}

	@Override
	public boolean equals(Object obj) {
		throw new RuntimeException("the method can't be used,please use isEqualTo() instead");
	}

	// @SuppressWarnings("unchecked")
	// public T match(Expectations expectations) {
	// if (this.type == AssertType.AssertThat) {
	// throw new RuntimeException("is not an Expectations");
	// } else {
	// expectations.with(this.link);
	// return (T) PrimitiveConvertor.value(valueClaz);
	// }
	// }

	@SuppressWarnings("unchecked")
	public T wanted() {
		if (this.type == AssertType.AssertThat) {
			throw new RuntimeException("is not an Expectations");
		} else {
			Expectations ex = ExpectationsUtil.getExpectations(Thread.currentThread().getId());
			ex.with(this.link);
			return (T) PrimitiveConvertor.value(valueClaz);
		}
	}
}
