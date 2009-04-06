package org.jtester.hamcrest.matcher;

import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MapMatcher extends BaseMatcher<Map<?, ?>> {
	private Object expected;

	private MapKeysMatcherType type;

	public MapMatcher(Object expected, MapKeysMatcherType type) {
		this.expected = expected;
		this.type = type;
	}

	public boolean matches(Object _actual) {
		if (!(_actual instanceof Map) && _actual == null) {
			return false;
		}
		Map<?, ?> actual = (Map<?, ?>) _actual;
		if (type == MapKeysMatcherType.KEY) {
			return actual.containsKey(expected);
		} else if (type == MapKeysMatcherType.VALUE) {
			return actual.containsValue(expected);
		} else {
			if (!(expected instanceof Entry)) {
				return false;
			}
			Entry<?, ?> entry = (Entry<?, ?>) expected;
			Object value = actual.get(entry.getKey());
			return entry.getValue().equals(value);
		}
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub

	}

	public static enum MapKeysMatcherType {
		KEY, VALUE, ENTRY;
	}

}
