package org.jtester.core;

import java.util.Collection;
import java.util.Map;

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
import org.jtester.hamcrest.iassert.object.impl.ObjectAssert;
import org.jtester.hamcrest.iassert.object.impl.ShortAssert;
import org.jtester.hamcrest.iassert.object.impl.StringAssert;
import org.jtester.utility.ArrayConvertor;

public interface JTesterAssert {
	public static class want {
		public static IStringAssert string(String value) {
			return new StringAssert(value);
		}

		public static IBooleanAssert bool(boolean value) {
			return new BooleanAssert(value);
		}

		public static IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value);
		}

		public static IIntegerAssert number(Integer value) {
			return new IntegerAssert(value);
		}

		public static IShortAssert number(Short value) {
			return new ShortAssert(value);
		}

		public static ILongAssert number(Long value) {
			return new LongAssert(value);
		}

		public static IDoubleAssert number(Double value) {
			return new DoubleAssert(value);
		}

		public static IFloatAssert number(Float value) {
			return new FloatAssert(value);
		}

		public static ICharacterAssert character(Character value) {
			return new CharacterAssert(value);
		}

		public static ICharacterAssert character(char value) {
			return new CharacterAssert(value);
		}

		/**
		 * bite is byte
		 * 
		 * @param value
		 * @return
		 */
		public static IByteAssert bite(Byte value) {
			return new ByteAssert(value);
		}

		/**
		 * bite is byte
		 * 
		 * @param value
		 * @return
		 */
		public static IByteAssert bite(byte value) {
			return new ByteAssert(value);
		}

		public static <T extends Object> IArrayAssert array(T value[]) {
			return new ArrayAssert(value);
		}

		// boolean
		public static IArrayAssert array(boolean value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		// byte
		public static IArrayAssert array(byte value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		// char
		public static IArrayAssert array(char value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		// short int long
		public static IArrayAssert array(short value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		public static IArrayAssert array(int value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		public static IArrayAssert array(long value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		// float double
		public static IArrayAssert array(float value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		public static IArrayAssert array(double value[]) {
			return new ArrayAssert(ArrayConvertor.convert(value));
		}

		public static IMapAssert map(Map<?, ?> map) {
			return new MapAssert(map);
		}

		public static ICollectionAssert collection(Collection<?> collection) {
			return new CollectionAssert(collection);
		}

		public static IObjectAssert object(Object bean) {
			return new ObjectAssert(bean);
		}

		public static void failure() {
			assert true == false;
		}
	}
}
