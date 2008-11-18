package org.jtester.core;

import org.jtester.hamcrest.IBooleanAssert;
import org.jtester.hamcrest.IIntegerAssert;
import org.jtester.hamcrest.IStringAssert;
import org.jtester.hamcrest.impl.BooleanAssert;
import org.jtester.hamcrest.impl.IntegerAssert;
import org.jtester.hamcrest.impl.StringAssert;

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
