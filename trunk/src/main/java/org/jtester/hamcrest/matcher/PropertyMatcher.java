package org.jtester.hamcrest.matcher;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jtester.core.ArrayConvertor;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;

@SuppressWarnings("unused")
public class PropertyMatcher<T> extends BaseMatcher<T> {
	private Object expected;

	private String message;

	private Set<ReflectionComparatorMode> modes = new HashSet<ReflectionComparatorMode>();

	private String property;

	private Difference difference;

	private ReflectionComparator reflectionComparator;

	public PropertyMatcher(String property, Object expected) {
		this.property = property;
		Object o = ArrayConvertor.convert(expected);
		if (o instanceof Object[]) {
			this.expected = ArrayConvertor.convert((Object[]) o);
		} else {
			this.expected = expected;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object item) {
		ReflectionAssert.assertPropertyLenientEquals(property, expected, item);
		return true;
	}

	public void describeTo(Description description) {
		// description.appendText(ReflectionAssertEx.formatMessageEx(message,
		// difference));
	}
}
