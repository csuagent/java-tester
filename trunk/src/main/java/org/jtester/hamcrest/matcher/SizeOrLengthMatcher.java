package org.jtester.hamcrest.matcher;

import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jtester.utility.ArrayUtil;

public class SizeOrLengthMatcher extends BaseMatcher<Collection<?>> {
	private int size;

	private MatchType type;

	public SizeOrLengthMatcher(int size, MatchType type) {
		this.size = size;
		this.type = type;
	}

	public boolean matches(Object actual) {
		if (actual == null) {
			return false;
		}
		int _size = ArrayUtil.sizeOf(actual);

		switch (type) {
		case EQ:
			return _size == size;
		case GT:
			return _size > size;
		case GE:
			return _size >= size;
		case LT:
			return _size < size;
		case LE:
			return _size <= size;
		default:
			return _size != size;
		}
	}

	public void describeTo(Description description) {
		description.appendText(String.format(type.description(), size));
	}

	public static enum MatchType {
		EQ {
			@Override
			public String description() {
				return "size of collection or array must equal to %d";
			}
		},
		GT {
			@Override
			public String description() {
				return "size of collection or array must be greater then %d";
			}
		},
		GE {
			@Override
			public String description() {
				return "size of collection or array must equal to or greater then %d";
			}
		},
		LT {
			@Override
			public String description() {
				return "size of collection or array must be less then %d";
			}
		},
		LE {
			@Override
			public String description() {
				return "size of collection or array must equal to or less then %d";
			}
		},
		NE {
			@Override
			public String description() {
				return "size of collection or array must not equal to %d";
			}
		};

		public abstract String description();
	}
}
