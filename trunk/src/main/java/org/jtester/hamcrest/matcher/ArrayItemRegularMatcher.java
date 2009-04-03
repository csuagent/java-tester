package org.jtester.hamcrest.matcher;

import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jtester.utility.ArrayUtil;

public class ArrayItemRegularMatcher extends BaseMatcher<Collection<?>> {
	private String regex = null;

	private Type type = null;

	public ArrayItemRegularMatcher(String regex, Type type) {
		this.regex = regex;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object actual) {
		if (actual == null) {
			return false;
		}
		Object[] _actual = null;
		if (ArrayUtil.isCollection(actual)) {
			_actual = ArrayUtil.convert((Collection) actual);
		} else if (ArrayUtil.isArray(actual)) {
			_actual = (Object[]) actual;
		} else {
			return false;
		}
		for (Object item : _actual) {
			boolean match = false;
			if (item != null) {
				match = item.toString().matches(regex);
			}
			if (match == false && type == Type.AND) {
				return false;
			}
			if (match == true && type == Type.OR) {
				return true;
			}
		}
		if (type == Type.AND) {
			return true;
		} else {
			return false;
		}
	}

	public void describeTo(Description description) {
		description.appendText(String.format(type.description(), regex));
	}

	public static enum Type {
		AND {
			@Override
			public String description() {
				return "all item must match regex %s";
			}
		},
		OR {
			@Override
			public String description() {
				return "has item must match regex %s";
			}
		};

		public abstract String description();
	}
}
