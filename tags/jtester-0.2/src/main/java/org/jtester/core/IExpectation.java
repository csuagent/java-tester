package org.jtester.core;

import org.hamcrest.Matcher;
import org.jtester.hamcrest.iassert.object.IArrayAssert;
import org.jtester.hamcrest.iassert.object.IBooleanAssert;
import org.jtester.hamcrest.iassert.object.IByteAssert;
import org.jtester.hamcrest.iassert.object.ICharacterAssert;
import org.jtester.hamcrest.iassert.object.ICollectionAssert;
import org.jtester.hamcrest.iassert.object.IDoubleAssert;
import org.jtester.hamcrest.iassert.object.IFloatAssert;
import org.jtester.hamcrest.iassert.object.IIntegerAssert;
import org.jtester.hamcrest.iassert.object.ILongAssert;
import org.jtester.hamcrest.iassert.object.IMapAssert;
import org.jtester.hamcrest.iassert.object.INumberAssert;
import org.jtester.hamcrest.iassert.object.IObjectAssert;
import org.jtester.hamcrest.iassert.object.IShortAssert;
import org.jtester.hamcrest.iassert.object.IStringAssert;
import org.jtester.hamcrest.iassert.object.impl.ArrayAssert;
import org.jtester.hamcrest.iassert.object.impl.BooleanAssert;
import org.jtester.hamcrest.iassert.object.impl.ByteAssert;
import org.jtester.hamcrest.iassert.object.impl.CharacterAssert;
import org.jtester.hamcrest.iassert.object.impl.CollectionAssert;
import org.jtester.hamcrest.iassert.object.impl.DoubleAssert;
import org.jtester.hamcrest.iassert.object.impl.FloatAssert;
import org.jtester.hamcrest.iassert.object.impl.IntegerAssert;
import org.jtester.hamcrest.iassert.object.impl.LongAssert;
import org.jtester.hamcrest.iassert.object.impl.MapAssert;
import org.jtester.hamcrest.iassert.object.impl.NumberAssert;
import org.jtester.hamcrest.iassert.object.impl.ObjectAssert;
import org.jtester.hamcrest.iassert.object.impl.ShortAssert;
import org.jtester.hamcrest.iassert.object.impl.StringAssert;
import org.mockito.internal.matchers.Any;

public interface IExpectation {
	public static class the {
		public static IStringAssert string() {
			return new StringAssert();
		}

		public static IBooleanAssert bool() {
			return new BooleanAssert();
		}

		@SuppressWarnings("unchecked")
		public static INumberAssert number() {
			return new NumberAssert(NumberAssert.class);
		}

		public static IIntegerAssert integer() {
			return new IntegerAssert();
		}

		public static ILongAssert longnum() {
			return new LongAssert();
		}

		public static IDoubleAssert doublenum() {
			return new DoubleAssert();
		}

		public static IFloatAssert floatnum() {
			return new FloatAssert();
		}

		public static IShortAssert shortnum() {
			return new ShortAssert();
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
			return new ByteAssert();
		}

		public static IArrayAssert array() {
			return new ArrayAssert();
		}

		public static IMapAssert map() {
			return new MapAssert();
		}

		public static ICollectionAssert collection() {
			return new CollectionAssert();
		}

		public static IObjectAssert object() {
			return new ObjectAssert();
		}

		@SuppressWarnings("unchecked")
		public static <T extends Object> Matcher<T> any(Class<T> claz) {
			Matcher<T> _matcher = Any.ANY;
			return _matcher;
		}
	}
}
