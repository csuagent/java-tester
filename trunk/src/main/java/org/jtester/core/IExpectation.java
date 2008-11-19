package org.jtester.core;

import org.jtester.hamcrest.IBooleanAssert;
import org.jtester.hamcrest.ICharacterAssert;
import org.jtester.hamcrest.IDoubleAssert;
import org.jtester.hamcrest.IFloatAssert;
import org.jtester.hamcrest.IIntegerAssert;
import org.jtester.hamcrest.IStringAssert;
import org.jtester.hamcrest.impl.BooleanAssert;
import org.jtester.hamcrest.impl.CharacterAssert;
import org.jtester.hamcrest.impl.DoubleAssert;
import org.jtester.hamcrest.impl.FloatAssert;
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

		public static IDoubleAssert doublenum() {
			return new DoubleAssert(DoubleAssert.class);
		}

		public static IFloatAssert floatnum() {
			return new FloatAssert(FloatAssert.class);
		}

		public static ICharacterAssert character() {
			return new CharacterAssert(CharacterAssert.class);
		}
	}
}
