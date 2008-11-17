package org.jtester.testng;

import org.jmock.Expectations;
import org.jtester.unitils.jmock.JmockUnitils;
import org.unitils.UnitilsTestNG;

public class JTester extends UnitilsTestNG {

	public static void checking(JExpectations expectations) {
		JmockUnitils.checking(expectations);
	}

	public static class JExpectations extends Expectations {

	}
}
