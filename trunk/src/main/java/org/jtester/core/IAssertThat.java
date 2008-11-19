package org.jtester.core;

import java.util.Collection;
import java.util.Map;

import org.jtester.hamcrest.IArrayAssert;
import org.jtester.hamcrest.IBooleanAssert;
import org.jtester.hamcrest.IByteAssert;
import org.jtester.hamcrest.ICharacterAssert;
import org.jtester.hamcrest.ICollectionAssert;
import org.jtester.hamcrest.IMapAssert;
import org.jtester.hamcrest.INumberAssert;
import org.jtester.hamcrest.IStringAssert;
import org.jtester.hamcrest.impl.ArrayAssert;
import org.jtester.hamcrest.impl.BooleanAssert;
import org.jtester.hamcrest.impl.ByteAssert;
import org.jtester.hamcrest.impl.CharacterAssert;
import org.jtester.hamcrest.impl.CollectionAssert;
import org.jtester.hamcrest.impl.MapAssert;
import org.jtester.hamcrest.impl.NumberAssert;
import org.jtester.hamcrest.impl.StringAssert;

public interface IAssertThat {
	// ImageAssert assertThat(BufferedImage actual);
	// FileAssert assertThat(File actual);
	// CollectionAssertEx assertThat(Iterator<?> actual);
	// ObjectAssertEx assertThat(Object actual);
	// <T extends AssertExtension> T assertThat(T assertion);
	// ThrowableAssert assertThat(Throwable actual);

	public static class want {
		public static IStringAssert string(String value) {
			return new StringAssert(value, StringAssert.class);
		}

		public static IBooleanAssert bool(boolean value) {
			return new BooleanAssert(value, BooleanAssert.class);
		}

		public static IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value, BooleanAssert.class);
		}

		public static INumberAssert number(Number value) {
			return new NumberAssert(value, INumberAssert.class);
		}

		public static INumberAssert number(int value) {
			return new NumberAssert(value, NumberAssert.class);
		}

		public static INumberAssert number(short value) {
			return new NumberAssert(value, INumberAssert.class);
		}

		public static INumberAssert number(long value) {
			return new NumberAssert(value, NumberAssert.class);
		}

		public static INumberAssert number(double value) {
			return new NumberAssert(value, NumberAssert.class);
		}

		public static INumberAssert number(float value) {
			return new NumberAssert(value, NumberAssert.class);
		}

		public static ICharacterAssert character(Character value) {
			return new CharacterAssert(value, CharacterAssert.class);
		}

		public static ICharacterAssert character(char value) {
			return new CharacterAssert(value, CharacterAssert.class);
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

		public static IArrayAssert array(Object value[]) {
			return new ArrayAssert(value, ArrayAssert.class);
		}

		// boolean
		public static IArrayAssert array(boolean value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		// byte
		public static IArrayAssert array(byte value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		// char
		public static IArrayAssert array(char value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		// short int long
		public static IArrayAssert array(short value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		public static IArrayAssert array(int value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		public static IArrayAssert array(long value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		// float double
		public static IArrayAssert array(float value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		public static IArrayAssert array(double value[]) {
			return new ArrayAssert(ConvertArray.convert(value), ArrayAssert.class);
		}

		public static IMapAssert map(Map map) {
			return new MapAssert(map, MapAssert.class);
		}

		public static ICollectionAssert collection(Collection collection) {
			return new CollectionAssert(collection, CollectionAssert.class);
		}
	}
}
