package org.jtester.core;

import org.jtester.jmock.JTesterExpectations;

public interface IJTester extends JTesterAssert, IExpectation {
	public static class Je extends JTesterExpectations {
	}
}
