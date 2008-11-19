package org.jtester.core;

import org.jtester.hamcrest.IBooleanAssert;
import org.jtester.hamcrest.ICharacterAssert;
import org.jtester.hamcrest.INumberAssert;
import org.jtester.hamcrest.IStringAssert;
import org.jtester.hamcrest.impl.BooleanAssert;
import org.jtester.hamcrest.impl.CharacterAssert;
import org.jtester.hamcrest.impl.NumberAssert;
import org.jtester.hamcrest.impl.StringAssert;

public interface IExpectation {
	public static class the {
		public static IStringAssert string() {
			return new StringAssert(IStringAssert.class);
		}

		public static IBooleanAssert bool() {
			return new BooleanAssert(BooleanAssert.class);
		}

		public static INumberAssert number() {
			return new NumberAssert(NumberAssert.class);
		}

		public static ICharacterAssert character() {
			return new CharacterAssert(CharacterAssert.class);
		}
	}
}
