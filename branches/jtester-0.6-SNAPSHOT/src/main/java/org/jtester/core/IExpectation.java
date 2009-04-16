package org.jtester.core;

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

/**
 * the jmock parameter expectation factory
 * 
 * @author darui.wudr
 * 
 */
public interface IExpectation {
	public static final TheExpectation the = new TheExpectation();

	public static class TheExpectation {
		/**
		 * a string parameter should be expected
		 * 
		 * @return
		 */
		public IStringAssert string() {
			return new StringAssert();
		}

		/**
		 * a boolean parameter should be expected
		 * 
		 * @return
		 */
		public IBooleanAssert bool() {
			return new BooleanAssert();
		}

		/**
		 * a number(integer, long, double,short,float) parameter should be
		 * expected
		 * 
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public INumberAssert number() {
			return new NumberAssert(NumberAssert.class);
		}

		/**
		 * a integer number parameter should be expected
		 * 
		 * @return
		 */
		public IIntegerAssert integer() {
			return new IntegerAssert();
		}

		/**
		 * a long number parameter should be expected
		 * 
		 * @return
		 */
		public ILongAssert longnum() {
			return new LongAssert();
		}

		/**
		 * a double number parameter should be expected
		 * 
		 * @return
		 */
		public IDoubleAssert doublenum() {
			return new DoubleAssert();
		}

		/**
		 * a float number parameter should be expected
		 * 
		 * @return
		 */
		public IFloatAssert floatnum() {
			return new FloatAssert();
		}

		/**
		 * a short number parameter should be expected
		 * 
		 * @return
		 */
		public IShortAssert shortnum() {
			return new ShortAssert();
		}

		/**
		 * a character parameter should be expected
		 * 
		 * @return
		 */
		public ICharacterAssert character() {
			return new CharacterAssert();
		}

		/**
		 * a bite parameter should be expected
		 * 
		 * @return
		 */
		public IByteAssert bite() {
			return new ByteAssert();
		}

		/**
		 * a array parameter should be expected
		 * 
		 * @return
		 */
		public IArrayAssert array() {
			return new ArrayAssert();
		}

		/**
		 * a map parameter should be expected
		 * 
		 * @return
		 */
		public IMapAssert map() {
			return new MapAssert();
		}

		/**
		 * a collection parameter should be expected
		 * 
		 * @return
		 */
		public ICollectionAssert collection() {
			return new CollectionAssert();
		}

		/**
		 * a general object parameter should be expected
		 * 
		 * @return
		 */
		public IObjectAssert object() {
			return new ObjectAssert();
		}
	}
}
