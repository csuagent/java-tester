package org.jtester.junit4;

import org.jtester.core.IJTester;
import org.jtester.jmock.JTesterExpectations;
import org.jtester.unitils.jmock.JmockUnitils;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4;
import org.unitils.UnitilsJUnit4TestClassRunner;

@RunWith(UnitilsJUnit4TestClassRunner.class)
public abstract class JTester extends UnitilsJUnit4 implements IJTester {
	public static void checking(JTesterExpectations expectations) {
		JmockUnitils.checking(expectations);
	}

	public static void fail() {
		assert true == false;
	}
}
