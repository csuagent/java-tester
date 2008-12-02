package org.jtester.testng;

import org.jmock.Expectations;
import org.jtester.core.IJTester;
import org.jtester.unitils.jmock.JmockUnitils;
import org.unitils.UnitilsTestNG;

public class JTester extends UnitilsTestNG implements IJTester {

	public static void checking(JTesterExpectations expectations) {
		JmockUnitils.checking(expectations);
	}

	public static void fail() {
		assert true == false;
	}

	public static class JTesterExpectations extends Expectations {
		// implements IExpectation
		protected Expectations $ = this;
	}

	public static class Je extends JTesterExpectations {
	}
}
