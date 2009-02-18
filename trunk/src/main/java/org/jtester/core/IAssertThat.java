package org.jtester.core;

import java.util.Collection;
import java.util.Map;

import org.jtester.hamcrest.iassert.IArrayAssert;
import org.jtester.hamcrest.iassert.IBooleanAssert;
import org.jtester.hamcrest.iassert.IByteAssert;
import org.jtester.hamcrest.iassert.ICharacterAssert;
import org.jtester.hamcrest.iassert.ICollectionAssert;
import org.jtester.hamcrest.iassert.IDoubleAssert;
import org.jtester.hamcrest.iassert.IFloatAssert;
import org.jtester.hamcrest.iassert.IIntegerAssert;
import org.jtester.hamcrest.iassert.ILongAssert;
import org.jtester.hamcrest.iassert.IMapAssert;
import org.jtester.hamcrest.iassert.IObjectAssert;
import org.jtester.hamcrest.iassert.IShortAssert;
import org.jtester.hamcrest.iassert.IStringAssert;
import org.jtester.hamcrest.iassert.impl.ArrayAssert;
import org.jtester.hamcrest.iassert.impl.BooleanAssert;
import org.jtester.hamcrest.iassert.impl.ByteAssert;
import org.jtester.hamcrest.iassert.impl.CharacterAssert;
import org.jtester.hamcrest.iassert.impl.CollectionAssert;
import org.jtester.hamcrest.iassert.impl.DoubleAssert;
import org.jtester.hamcrest.iassert.impl.FloatAssert;
import org.jtester.hamcrest.iassert.impl.IntegerAssert;
import org.jtester.hamcrest.iassert.impl.LongAssert;
import org.jtester.hamcrest.iassert.impl.MapAssert;
import org.jtester.hamcrest.iassert.impl.ObjectAssert;
import org.jtester.hamcrest.iassert.impl.ShortAssert;
import org.jtester.hamcrest.iassert.impl.StringAssert;

public interface IAssertThat {
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
			return new ByteAssert(value, ByteAssert.class);
		}

		/**
		 * bite is byte
		 * 
		 * @param value
		 * @return
		 */
		public static IByteAssert bite(byte value) {
			return new ByteAssert(value, ByteAssert.class);
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
			return new CollectionAssert(collection, ICollectionAssert.class);
		}

		public static IObjectAssert object(Object bean) {
			return new ObjectAssert(bean);
		}
	}
}
