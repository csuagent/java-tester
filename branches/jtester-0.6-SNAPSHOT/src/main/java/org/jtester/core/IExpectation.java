package org.jtester.core;

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
import org.jtester.hamcrest.iassert.object.intf.IArrayAssert;
import org.jtester.hamcrest.iassert.object.intf.IBooleanAssert;
import org.jtester.hamcrest.iassert.object.intf.IByteAssert;
import org.jtester.hamcrest.iassert.object.intf.ICharacterAssert;
import org.jtester.hamcrest.iassert.object.intf.ICollectionAssert;
import org.jtester.hamcrest.iassert.object.intf.IDoubleAssert;
import org.jtester.hamcrest.iassert.object.intf.IFloatAssert;
import org.jtester.hamcrest.iassert.object.intf.IIntegerAssert;
import org.jtester.hamcrest.iassert.object.intf.ILongAssert;
import org.jtester.hamcrest.iassert.object.intf.IMapAssert;
import org.jtester.hamcrest.iassert.object.intf.INumberAssert;
import org.jtester.hamcrest.iassert.object.intf.IObjectAssert;
import org.jtester.hamcrest.iassert.object.intf.IShortAssert;
import org.jtester.hamcrest.iassert.object.intf.IStringAssert;

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
