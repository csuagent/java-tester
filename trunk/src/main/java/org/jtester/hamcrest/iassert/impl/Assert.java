package org.jtester.hamcrest.iassert.impl;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.jmock.Expectations;
import org.jtester.core.PrimitiveConvertor;
import org.jtester.hamcrest.iassert.IAssert;
import org.jtester.unitils.jmock.matcher.ILinkMatcher;
import org.jtester.unitils.jmock.matcher.impl.LinkMatcher;

public abstract class Assert<T, E extends IAssert<T, ?>> extends BaseMatcher<T> implements IAssert<T, E> {

	protected Class<T> clazT;

	protected Object value;

	protected AssertType type;

	@SuppressWarnings("unchecked")
	protected Class<? extends IAssert> clazE;

	protected ILinkMatcher<T> link;

	public Assert(Class<? extends IAssert> clazE) {
		this.value = null;
		this.type = AssertType.Expectations;
		this.link = new LinkMatcher<T>();
		this.clazE = clazE;
	}

	public Assert(T value, Class<? extends IAssert> clazE) {
		this.type = AssertType.AssertThat;
		this.value = value;
		this.clazE = clazE;
	}

	@SuppressWarnings("unchecked")
	public Assert(Class<T> clazT, Class<? extends IAssert> clazE) {
		this.type = AssertType.Expectations;
		this.clazT = clazT;
		this.clazE = clazE;
		this.link = new LinkMatcher<T>();
	}

	@SuppressWarnings("unchecked")
	public T match(Expectations expectations) {
		if (this.type == AssertType.AssertThat) {
			throw new RuntimeException("is not an Expectations");
		} else {
			expectations.with(this.link);
			return PrimitiveConvertor.value(clazT);
		}
	}
	
	public T $(Expectations expectations) {
		return this.match(expectations);
	}

	public void describeTo(Description description) {
		link.describeTo(description);
	}

	public E assertThat(Matcher matcher) {
		if (this.type == AssertType.AssertThat) {
			MatcherAssert.assertThat(this.value, matcher);
		} else {
			this.link.add(matcher);
		}
		return (E) this;
	}

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
}
