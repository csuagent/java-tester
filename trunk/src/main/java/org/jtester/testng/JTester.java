package org.jtester.testng;

import org.jmock.Expectations;
import org.jtester.unitils.jmock.JmockUnitils;
import org.jtester.unitils.jmock.fluent.IHamcrestAssert;
import org.jtester.unitils.jmock.fluent.IStringAssert;
import org.jtester.unitils.jmock.fluent.impl.HamcrestAssert;
import org.unitils.UnitilsTestNG;

public class JTester extends UnitilsTestNG implements IHamcrestAssert {

	public static void checking(JExpectations expectations) {
		JmockUnitils.checking(expectations);
	}

	public static class JExpectations extends Expectations {

	}

	// assert that
	private IHamcrestAssert hamcrestAssert = new HamcrestAssert();

	public IStringAssert assertThat(String value) {
		return hamcrestAssert.assertThat(value);
	}
}
