package org.jtester.hamcrest.matcher;

import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jtester.utility.ArrayUtil;

public class SizeOrLengthMatcher extends BaseMatcher<Collection<?>> {
	private int size;

	public SizeOrLengthMatcher(int size) {
		this.size = size;
	}

	public boolean matches(Object actual) {
		if (actual == null) {
			return false;
		}
		int _size = ArrayUtil.sizeOf(actual);

		return _size == size;
	}

	public void describeTo(Description description) {
		description.appendText(String.format("collection size must equal to %d", size));
	}
}
