package org.jtester.core;

import org.jtester.hamcrest.IBooleanAssert;
import org.jtester.hamcrest.IDoubleAssert;
import org.jtester.hamcrest.IFloatAssert;
import org.jtester.hamcrest.IIntegerAssert;
import org.jtester.hamcrest.IStringAssert;
import org.jtester.hamcrest.impl.BooleanAssert;
import org.jtester.hamcrest.impl.DoubleAssert;
import org.jtester.hamcrest.impl.FloatAssert;
import org.jtester.hamcrest.impl.IntegerAssert;
import org.jtester.hamcrest.impl.StringAssert;

public interface IAssertThat {
	// BigDecimalAssert assertThat(BigDecimal actual);
	//
	// BooleanArrayAssert assertThat(boolean[] actual);
	//
	// ImageAssert assertThat(BufferedImage actual);
	//
	// ByteAssert assertThat(byte actual);
	//
	// ByteArrayAssert assertThat(byte[] actual);
	//
	// CharAssert assertThat(char actual);
	//
	// CharArrayAssert assertThat(char[] actual);
	//
	// CollectionAssertEx assertThat(Collection<?> actual);
	//
	// DoubleAssert assertThat(double actual);
	//
	// DoubleArrayAssert assertThat(double[] actual);
	//
	// FileAssert assertThat(File actual);
	//
	// FloatAssert assertThat(float actual);
	//
	// FloatArrayAssert assertThat(float[] actual);

	//
	// IntArrayAssert assertThat(int[] actual);
	//
	// CollectionAssertEx assertThat(Iterator<?> actual);
	//
	// LongAssert assertThat(long actual);
	//
	// LongArrayAssert assertThat(long[] actual);
	//
	// MapAssert assertThat(Map<?, ?> actual);
	//
	// ObjectAssertEx assertThat(Object actual);
	//
	// ObjectArrayAssertEx assertThat(Object[] actual);
	//
	// ShortAssert assertThat(short actual);
	//
	// ShortArrayAssert assertThat(short[] actual);
	//
	// StringAssert assertThat(String actual);
	//
	// <T extends AssertExtension> T assertThat(T assertion);
	//
	// ThrowableAssert assertThat(Throwable actual);

	public static class want {
		public static IStringAssert string(String value) {
			return new StringAssert(value, StringAssert.class);
		}

		public static IIntegerAssert integer(int value) {
			return new IntegerAssert(value, IntegerAssert.class);
		}

		public static IIntegerAssert integer(Integer value) {
			return new IntegerAssert(value, IntegerAssert.class);
		}

		public static IBooleanAssert bool(boolean value) {
			return new BooleanAssert(value, BooleanAssert.class);
		}

		public static IBooleanAssert bool(Boolean value) {
			return new BooleanAssert(value, BooleanAssert.class);
		}

		public static IDoubleAssert doublenum(double value) {
			return new DoubleAssert(value, DoubleAssert.class);
		}

		public static IDoubleAssert doublenum(Double value) {
			return new DoubleAssert(value, DoubleAssert.class);
		}

		public static IFloatAssert floatnum(float value) {
			return new FloatAssert(value, FloatAssert.class);
		}

		public static IFloatAssert floatnum(Float value) {
			return new FloatAssert(value, FloatAssert.class);
		}
	}
}
