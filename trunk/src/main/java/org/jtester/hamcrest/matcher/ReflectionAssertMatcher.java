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

public class ReflectionAssertMatcher<T> extends BaseMatcher<T> {
	private Object ref;

	private Set<ReflectionComparatorMode> modes = new HashSet<ReflectionComparatorMode>();

	private String property;

	private Difference difference;

	private ReflectionComparator reflectionComparator;

	public ReflectionAssertMatcher() {
		this.ref = null;
	}

	public ReflectionAssertMatcher(String property, Object ref) {
		this.property = property;
		this.ref = ref;
	}

	@SuppressWarnings("unchecked")
	public boolean matches(Object item) {
		this.reflectionComparator = ReflectionComparatorFactory.createRefectionComparator(modes
				.toArray(new ReflectionComparatorMode[0]));
		if (property == null) {
			difference = reflectionComparator.getDifference(item, ref);
		} else if ((item instanceof Object[] || item instanceof Collection)
				&& (ref instanceof Object[] || ref instanceof Collection)) {
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
		if (ref instanceof Object[]) {
			ref = Arrays.asList((Object[]) ref);
		}
		Collection<?> _item = CollectionUtils.collect(item, new ReflectionAssertEx.OgnlTransformerEx(this.property));
		try {
			Collection<?> _ref = CollectionUtils.collect((Collection<Object>) ref,
					new ReflectionAssertEx.OgnlTransformerEx(this.property));
			difference = reflectionComparator.getDifference(_item, _ref);
		} catch (UnitilsException e) {
			difference = reflectionComparator.getDifference(_item, ref);
		}
	}

	private void matchesProperty(Object item) {
		if (item == null) {
			throw new RuntimeException("Actual object can't be null.");
		}
		Object _expect = ReflectionAssertEx.getPropertyEx(item, property);
		try {
			Object _ref = ReflectionAssertEx.getPropertyEx(ref, property);
			difference = this.reflectionComparator.getDifference(_expect, _ref);
		} catch (UnitilsException e) {
			difference = this.reflectionComparator.getDifference(_expect, ref);
		}
	}

	public ReflectionAssertMatcher<T> ignoreDefaults() {
		this.modes.add(ReflectionComparatorMode.IGNORE_DEFAULTS);
		return this;
	}

	public ReflectionAssertMatcher<T> lenientDates() {
		this.modes.add(ReflectionComparatorMode.LENIENT_DATES);
		return this;
	}

	public ReflectionAssertMatcher<T> lenientOrder() {
		this.modes.add(ReflectionComparatorMode.LENIENT_ORDER);
		return this;
	}

	public void describeTo(Description description) {
		description.appendText(difference.getMessage());
	}

	public static <T extends Object> ReflectionAssertMatcher<T> refEquals(Object ref) {
		if (ref == null) {
			throw new RuntimeException("compare object can't be null.");
		}
		return new ReflectionAssertMatcher<T>(null, ref);
	}

	private static class ReflectionAssertEx extends ReflectionAssert {
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
