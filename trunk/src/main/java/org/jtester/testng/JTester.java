package org.jtester.testng;

import org.jmock.Expectations;
import org.jtester.unitils.jmock.JmockUnitils;
import org.jtester.unitils.jmock.fluent.IHamcrestAssert;
import org.jtester.unitils.jmock.fluent.IStringAssert;
import org.jtester.unitils.jmock.fluent.impl.HamcrestAssert;
import org.jtester.unitils.jmock.fluent.impl.StringAssert;
import org.unitils.UnitilsTestNG;

public class JTester extends UnitilsTestNG implements IHamcrestAssert {

	public static void checking(JExpectations expectations) {
		JmockUnitils.checking(expectations);
	}

	public static class JExpectations extends Expectations {

	}

	// assert that
	private IHamcrestAssert hamcrestAssert = new HamcrestAssert();

	public IStringAssert want(String value) {
		return hamcrestAssert.want(value);
	}

	protected AssertType the() {
		return new AssertType();
	}

	protected static class AssertType {
		public IStringAssert string;

		public AssertType() {
			string = new StringAssert(IStringAssert.class);
		}
	}
}
