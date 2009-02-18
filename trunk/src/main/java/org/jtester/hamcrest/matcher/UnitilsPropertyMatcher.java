package org.jtester.hamcrest.matcher;

import static junit.framework.Assert.assertNotNull;
import static org.unitils.reflectionassert.ReflectionComparatorFactory.createRefectionComparator;

import java.util.ArrayList;
import java.util.Collection;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jtester.utility.ReflectUtil;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;
import org.unitils.reflectionassert.report.DifferenceReport;
import org.unitils.reflectionassert.report.impl.DefaultDifferenceReport;

public class UnitilsPropertyMatcher extends BaseMatcher<Object> {
	private Object expected;

	private String property;

	private ReflectionComparatorMode[] modes;

	public UnitilsPropertyMatcher(String property, Object expected,
			ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	public UnitilsPropertyMatcher(String property, Collection<?> expected,
			ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	public <T extends Object> UnitilsPropertyMatcher(String property,
			T[] expected, ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	private Difference difference;

	public boolean matches(Object actual) {
		assertNotNull("Actual object is null.", actual);
		// Object propertyValue = getProperty(actualObject, propertyName);
		try {
			Collection<?> _actualProps = this.getProperty(actual);
			Collection<?> _expectedProps = this.getProperty(expected);

			ReflectionComparator reflectionComparator = createRefectionComparator(modes);
			this.difference = reflectionComparator.getDifference(
					_expectedProps, _actualProps);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return difference == null;
	}

	public void describeTo(Description description) {
		if (difference != null) {
			String message = "Incorrect value for property: " + this.property;
			DifferenceReport differenceReport = new DefaultDifferenceReport();
			description.appendText(differenceReport.createReport(message,
					difference));
		}
	}

	public Collection<?> getProperty(Object o) throws SecurityException,
			NoSuchFieldException {
		Collection<Object> coll = new ArrayList<Object>();
		if (o == null) {
			return coll;
		} else if (o instanceof Collection) {
			Collection<?> oc = (Collection<?>) o;
			for (Object o1 : oc) {
				Object value = ReflectUtil.getFieldValue(o1, this.property);
				coll.add(value);
			}
		} else if (o instanceof Object[]) {
			Object[] oa = (Object[]) o;
			for (Object o2 : oa) {
				Object value = ReflectUtil.getFieldValue(o2, this.property);
				coll.add(value);
			}
		} else {
			Object value = ReflectUtil.getFieldValue(o, this.property);
			coll.add(value);
		}
		return coll;
	}
}
