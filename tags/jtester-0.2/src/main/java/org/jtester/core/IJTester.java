package org.jtester.core;

import org.jtester.jmock.JTesterExpectations;
import org.jtester.unitils.jmock.JmockUnitils;

public interface IJTester extends IAssertThat, IAssertOptions, IExpectation {
	public static class Je extends JTesterExpectations {
		public static void checking(JTesterExpectations expectations) {
			JmockUnitils.checking(expectations);
		}
	}
}
