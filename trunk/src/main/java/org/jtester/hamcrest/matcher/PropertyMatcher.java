package org.jtester.hamcrest.matcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.unitils.core.UnitilsException;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorFactory;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;

public class PropertyMatcher<T> extends BaseMatcher<T> {
	private Object expectedValue;

	private Set<ReflectionComparatorMode> modes = new HashSet<ReflectionComparatorMode>();

	private String property;

	private Difference difference;

	private ReflectionComparator reflectionComparator;

	public PropertyMatcher(Object expectedValue) {
		this.property = null;
		this.expectedValue = expectedValue;
	}

	public PropertyMatcher(String property, Object expectedValue) {
		this.property = property;
		this.expectedValue = expectedValue;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object item) {
		this.reflectionComparator = ReflectionComparatorFactory.createRefectionComparator(modes
				.toArray(new ReflectionComparatorMode[0]));
		if (property == null) {
			difference = reflectionComparator.getDifference(item, expectedValue);
		} else if ((item instanceof Object[] || item instanceof Collection)
				&& (expectedValue instanceof Object[] || expectedValue instanceof Collection)) {
			if (item instanceof Object[]) {
				matchesCollection(Arrays.asList((Object[]) item));
			} else {
				matchesCollection((Collection<Object>) item);
			}
		} else {
			matchesProperty(item);
		}
		return difference == null;
	}

	@SuppressWarnings("unchecked")
	private void matchesCollection(Collection<Object> item) {
		if (expectedValue instanceof Object[]) {
			expectedValue = Arrays.asList((Object[]) expectedValue);
		}
		Collection<?> _item = CollectionUtils.collect(item,
				new JTesterReflectionAssert.OgnlTransformerEx(this.property));
		try {
			Collection<?> _ref = CollectionUtils.collect((Collection<Object>) expectedValue,
					new JTesterReflectionAssert.OgnlTransformerEx(this.property));
			difference = reflectionComparator.getDifference(_item, _ref);
		} catch (UnitilsException e) {
			difference = reflectionComparator.getDifference(_item, expectedValue);
		}
	}

	private void matchesProperty(Object item) {
		if (item == null) {
			throw new RuntimeException("Actual object can't be null.");
		}
		Object _expect = JTesterReflectionAssert.getPropertyEx(item, property);
		try {
			Object _ref = JTesterReflectionAssert.getPropertyEx(expectedValue, property);
			difference = this.reflectionComparator.getDifference(_expect, _ref);
		} catch (UnitilsException e) {
			difference = this.reflectionComparator.getDifference(_expect, expectedValue);
		}
	}

	public PropertyMatcher<T> ignoreDefaults() {
		this.modes.add(ReflectionComparatorMode.IGNORE_DEFAULTS);
		return this;
	}

	public PropertyMatcher<T> lenientDates() {
		this.modes.add(ReflectionComparatorMode.LENIENT_DATES);
		return this;
	}

	public PropertyMatcher<T> lenientOrder() {
		this.modes.add(ReflectionComparatorMode.LENIENT_ORDER);
		return this;
	}

	public void describeTo(Description description) {
		description.appendText(difference.getMessage());
	}

	public static <T extends Object> PropertyMatcher<T> refEquals(Object ref) {
		if (ref == null) {
			throw new RuntimeException("compare object can't be null.");
		}
		return new PropertyMatcher<T>(null, ref);
	}

	private static class JTesterReflectionAssert extends ReflectionAssert {
		public static Object getPropertyEx(Object object, String ognlExpression) {
			return getProperty(object, ognlExpression);
		}

		public static class OgnlTransformerEx extends OgnlTransformer {
			public OgnlTransformerEx(String arg0) {
				super(arg0);
			}
		}
	}
}
