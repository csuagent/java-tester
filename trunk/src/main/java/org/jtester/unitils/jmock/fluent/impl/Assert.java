package org.jtester.unitils.jmock.fluent.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.jmock.Expectations;
import org.jtester.unitils.jmock.fluent.IAssert;

public abstract class Assert<T, E extends IAssert<T, E>> implements Matcher<T> {

	protected Class<T> clazT;

	@SuppressWarnings("unchecked")
	protected Class<? extends IAssert> clazE;

	private Description description = null;

	private List<Matcher<?>> matchers = null;

	private boolean recordDescriptionStatus = false;

	private boolean matched = true;

	private MatchNodeType prevNodeType = MatchNodeType.Start;

	private Assert<T, E> orExpected = null;

	private Assert<T, E> notExpected = null;

	private Assert<T, E> rootExpected = null;

	@SuppressWarnings("unchecked")
	public Assert(Class<T> clazT, Class<? extends IAssert> clazE) {
		this.clazT = clazT;
		this.clazE = clazE;
		this.rootExpected = this;
		this.prevNodeType = MatchNodeType.Start;
		this.description = new StringDescription();
		this.matchers = new ArrayList<Matcher<?>>();
	}

	protected abstract Assert<T, E> getInstance();

	@SuppressWarnings("unchecked")
	protected final E instance() {
		Assert<T, E> instance = this.getInstance();
		instance.setRootExpected(this.rootExpected);

		return (E) instance;
	}

	public final Assert<T, E> matcher() {
		if (this.prevNodeType == MatchNodeType.Or) {
			throw new RuntimeException("expected expression error:no succeeded condiction can be or");
		} else if (this.prevNodeType == MatchNodeType.Not) {
			throw new RuntimeException("expected expression error:no succeeded condiction can be not");
		}
		return (Assert<T, E>) this;
	}

	@SuppressWarnings("unchecked")
	public T match(Expectations expectations) {
		if (this.prevNodeType == MatchNodeType.Or) {
			throw new RuntimeException("expected expression error:no succeeded condiction can be or");
		} else if (this.prevNodeType == MatchNodeType.Not) {
			throw new RuntimeException("expected expression error:no succeeded condiction can be not");
		}
		if (this.rootExpected == null) {
			throw new RuntimeException("the root BaseExpected can't be null");
		}
		expectations.with(this.rootExpected);
		if (map.containsKey(clazT)) {
			return (T) map.get(clazT);
		} else {
			return null;
		}
	}

	public boolean matches(Object item) {
		if (this.orExpected != null) {
			this.description.appendText("(");
		}
		for (Matcher<?> matcher : this.matchers) {
			this.matched = matcher.matches(item);
			if (this.matched == this.recordDescriptionStatus) {
				matcher.describeTo(this.description);
				break;
			}
		}
		if (this.matched == false && this.orExpected != null) {
			this.matched = this.orExpected.matches(item);
			this.description.appendText(" or ");
			this.orExpected.describeTo(this.description);
		}
		if (this.orExpected != null) {
			this.description.appendText(")");
		}
		return this.matched;
	}

	public void describeTo(Description description) {
		description.appendText(this.description.toString());
	}

	@SuppressWarnings("unchecked")
	protected E link(Matcher<?> matcher) {
		if (matcher == null) {
			throw new RuntimeException("can't add a null Matcher");
		}
		this.matchers.add(matcher);
		this.prevNodeType = MatchNodeType.Normal;

		if (this.notExpected != null) {
			this.notExpected.prevNodeType = MatchNodeType.Normal;
			return (E) this.notExpected;
		}
		return (E) this;
	}

	protected void addMatcher(Matcher<?> matcher) {
		this.matchers.add(matcher);
	}

	protected void setOrExpected(Assert<T, E> orExpected) {
		this.orExpected = orExpected;
	}

	protected void setNotExpected(Assert<T, E> notExpected) {
		this.notExpected = notExpected;
	}

	protected void setPrevNodeType(MatchNodeType prevNodeType) {
		this.prevNodeType = prevNodeType;
	}

	protected void setRecordDescriptionStatus(boolean status) {
		this.recordDescriptionStatus = status;
	}

	protected MatchNodeType getPrevNodeType() {
		return this.prevNodeType;
	}

	protected void setRootExpected(Assert<T, E> root) {
		this.rootExpected = root;
	}

	public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
		// See Matcher interface for an explanation of this method.
	}

	@Override
	public String toString() {
		return StringDescription.toString(this);
	}

	protected static enum MatchNodeType {
		Or, Not, Normal, Start;
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

}
