package org.jtester.unitils.jmock.fluent.impl;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.jmock.Expectations;
import org.jtester.unitils.jmock.fluent.IAssert;
import org.jtester.unitils.jmock.matcher.ILinkMatcher;
import org.jtester.unitils.jmock.matcher.impl.LinkMatcher;

public abstract class Assert<T, E extends IAssert<T, ?>> extends BaseMatcher<T> implements IAssert<T, E> {

	protected Class<T> clazT;

	protected T value;

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

	protected abstract E getInstance();

	@SuppressWarnings("unchecked")
	protected final E instance() {
		E instance = this.getInstance();
		return instance;
	}

	@SuppressWarnings("unchecked")
	public T match(Expectations expectations) {
		if (this.type == AssertType.AssertThat) {
			throw new RuntimeException("is not an Expectations");
		} else {
			expectations.with(this.link);
			if (map.containsKey(clazT)) {
				return (T) map.get(clazT);
			} else {
				return null;
			}
		}
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

	public E assertThat(T value, Matcher matcher) {
		if (this.type == AssertType.AssertThat) {
			MatcherAssert.assertThat(value, matcher);
		} else {
			this.link.add(matcher);
		}
		return (E) this;
	}

	public boolean matches(Object item) {
		return this.link.matches(item);
	}

	private static Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
	static {
		map.put(String.class, "");
		map.put(Integer.class, 0);
		map.put(Short.class, 0);
		map.put(Long.class, 0);
		map.put(Byte.class, 0);
		map.put(Float.class, 0.0f);
		map.put(Double.class, 0.0d);

		map.put(Boolean.class, false);

		// TODO
	}

	private static enum AssertType {
		AssertThat, Expectations;
	}

	public E setValue(T value) {
		this.value = value;
		return (E) this;
	}
}
