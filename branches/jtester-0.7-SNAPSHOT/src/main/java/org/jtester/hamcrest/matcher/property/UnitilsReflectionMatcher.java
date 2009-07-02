package org.jtester.hamcrest.matcher.property;

import static org.unitils.reflectionassert.ReflectionComparatorFactory.createRefectionComparator;

import java.util.Collection;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;
import org.unitils.reflectionassert.report.DifferenceReport;
import org.unitils.reflectionassert.report.impl.DefaultDifferenceReport;

public class UnitilsReflectionMatcher extends BaseMatcher<Object> {
	private Object expected;

	private ReflectionComparatorMode[] modes;

	public UnitilsReflectionMatcher(Object expected,
			ReflectionComparatorMode[] modes) {
		this.expected = expected;
		this.modes = modes;
	}

	public UnitilsReflectionMatcher(Collection<?> expected,
			ReflectionComparatorMode[] modes) {
		this.expected = expected;
		this.modes = modes;
	}

	public <T extends Object> UnitilsReflectionMatcher(T[] expected,
			ReflectionComparatorMode[] modes) {
		this.expected = expected;
		this.modes = modes;
	}

	public UnitilsReflectionMatcher(List<?> expected,
			ReflectionComparatorMode[] modes) {
		this.expected = expected;
		this.modes = modes;
	}

	private Difference difference;

	public boolean matches(Object actual) {
		ReflectionComparator reflectionComparator = createRefectionComparator(modes);
		this.difference = reflectionComparator.getDifference(expected, actual);
		return difference == null;
	}

	public void describeTo(Description description) {
		if (difference != null) {
			DifferenceReport differenceReport = new DefaultDifferenceReport();
			description.appendText(differenceReport.createReport(null,
					difference));
		}
	}
}
