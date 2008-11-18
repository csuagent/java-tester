package org.jtester.unitils.jmock.fluent;

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
	}
}
