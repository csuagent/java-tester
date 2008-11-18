package org.jtester.core;

import org.jtester.unitils.jmock.fluent.IBooleanAssert;
import org.jtester.unitils.jmock.fluent.IIntegerAssert;
import org.jtester.unitils.jmock.fluent.IStringAssert;
import org.jtester.unitils.jmock.fluent.impl.BooleanAssert;
import org.jtester.unitils.jmock.fluent.impl.IntegerAssert;
import org.jtester.unitils.jmock.fluent.impl.StringAssert;

public interface IExpectation {
	public static class the {
		public static IStringAssert string() {
			return new StringAssert(IStringAssert.class);
		}

		public static IIntegerAssert integer() {
			return new IntegerAssert(IIntegerAssert.class);
		}

		public static IBooleanAssert bool() {
			return new BooleanAssert(BooleanAssert.class);
		}
	}
}
