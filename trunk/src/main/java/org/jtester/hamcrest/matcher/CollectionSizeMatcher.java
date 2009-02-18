package org.jtester.hamcrest.matcher;

import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class CollectionSizeMatcher extends BaseMatcher<Collection<?>> {
	private int size;

	public CollectionSizeMatcher(int size) {
		this.size = size;
	}

	public boolean matches(Object actual) {
		int _size = 0;
		if (actual != null && actual instanceof Collection) {
			_size = ((Collection<?>) actual).size();
		}
		return _size == size;
	}

	public void describeTo(Description description) {
		description.appendText(String.format(
				"collection size must equal to %d", size));
	}
}
