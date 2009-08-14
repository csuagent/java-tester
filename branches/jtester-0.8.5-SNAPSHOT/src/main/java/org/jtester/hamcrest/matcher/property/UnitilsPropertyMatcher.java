package org.jtester.hamcrest.matcher.property;

//import static junit.framework.Assert.assertNotNull;
import static org.unitils.reflectionassert.ReflectionComparatorFactory.createRefectionComparator;

import java.util.ArrayList;
import java.util.Collection;

import ognl.DefaultMemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.unitils.reflectionassert.ReflectionComparator;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import org.unitils.reflectionassert.difference.Difference;
import org.unitils.reflectionassert.report.DifferenceReport;
import org.unitils.reflectionassert.report.impl.DefaultDifferenceReport;

public class UnitilsPropertyMatcher extends BaseMatcher<Object> {
	private Object expected;

	private String property;

	private ReflectionComparatorMode[] modes;

	public UnitilsPropertyMatcher(String property, Object expected, ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	public UnitilsPropertyMatcher(String property, Collection<?> expected, ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	public <T extends Object> UnitilsPropertyMatcher(String property, T[] expected, ReflectionComparatorMode[] modes) {
		this.property = property;
		this.expected = expected;
		this.modes = modes;
	}

	private Difference difference;

	public boolean matches(Object actual) {
		if( actual == null) {
			throw new RuntimeException("actual object can't be null!");
		}
		// Object propertyValue = getProperty(actualObject, propertyName);
		Collection<?> _actualProps = this.getProperty(actual);
		Collection<?> _expectedProps = this.getProperty(expected);

		ReflectionComparator reflectionComparator = createRefectionComparator(modes);
		this.difference = reflectionComparator.getDifference(_expectedProps, _actualProps);

		return difference == null;
	}

	public void describeTo(Description description) {
		if (difference != null) {
			String message = "Incorrect value for property: " + this.property;
			DifferenceReport differenceReport = new DefaultDifferenceReport();
			description.appendText(differenceReport.createReport(message, difference));
		}
	}

	private Collection<?> getProperty(Object o) {
		Collection<Object> coll = new ArrayList<Object>();
		if (o == null) {
			coll.add(null);
		} else if (o instanceof Collection<?>) {
			Collection<?> oc = (Collection<?>) o;
			for (Object o1 : oc) {
				Object value = getProperty(o1, this.property);
				coll.add(value);
			}
		} else if (o instanceof Object[]) {
			Object[] oa = (Object[]) o;
			for (Object o2 : oa) {
				Object value = getProperty(o2, this.property);
				coll.add(value);
			}
		} else {
			Object value = getProperty(o, this.property);
			coll.add(value);
		}
		return coll;
	}

	private static Object getProperty(Object object, String ognlExpression) {
		try {
			OgnlContext ognlContext = new OgnlContext();
			ognlContext.setMemberAccess(new DefaultMemberAccess(true));
			Object ognlExprObj = Ognl.parseExpression(ognlExpression);
			return Ognl.getValue(ognlExprObj, ognlContext, object);
		} catch (OgnlException e) {
			return object;
		} catch (Throwable e) {
			// e.printStackTrace();
			return object;
		}
	}
}
