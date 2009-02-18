package org.jtester.core;

import org.jtester.hamcrest.iassert.IArrayAssert;
import org.jtester.hamcrest.iassert.IBooleanAssert;
import org.jtester.hamcrest.iassert.IByteAssert;
import org.jtester.hamcrest.iassert.ICharacterAssert;
import org.jtester.hamcrest.iassert.ICollectionAssert;
import org.jtester.hamcrest.iassert.IMapAssert;
import org.jtester.hamcrest.iassert.INumberAssert;
import org.jtester.hamcrest.iassert.IObjectAssert;
import org.jtester.hamcrest.iassert.IStringAssert;
import org.jtester.hamcrest.iassert.impl.ArrayAssert;
import org.jtester.hamcrest.iassert.impl.BooleanAssert;
import org.jtester.hamcrest.iassert.impl.ByteAssert;
import org.jtester.hamcrest.iassert.impl.CharacterAssert;
import org.jtester.hamcrest.iassert.impl.CollectionAssert;
import org.jtester.hamcrest.iassert.impl.MapAssert;
import org.jtester.hamcrest.iassert.impl.NumberAssert;
import org.jtester.hamcrest.iassert.impl.ObjectAssert;
import org.jtester.hamcrest.iassert.impl.StringAssert;

public interface IExpectation {
	public static class the {
		public static IStringAssert string() {
			return new StringAssert();
		}

		public static IBooleanAssert bool() {
			return new BooleanAssert();
		}

		public static INumberAssert number() {
			return new NumberAssert(NumberAssert.class);
		}

		public static ICharacterAssert character() {
			return new CharacterAssert();
		}

		/**
		 * bite is byte
		 * 
		 * @return
		 */
		public static IByteAssert bite() {
			return new ByteAssert(ByteAssert.class);
		}

		public static IArrayAssert array() {
			return new ArrayAssert();
		}

		public static IMapAssert map() {
			return new MapAssert();
		}

		public static ICollectionAssert collection() {
			return new CollectionAssert(CollectionAssert.class);
		}

		public static IObjectAssert object() {
			return new ObjectAssert();
		}
	}
}
