package org.jtester.utility;

import java.util.Arrays;
import java.util.Collection;

import org.jtester.testng.JTester;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ArrayUtilTest extends JTester {
	@Test(dataProvider = "array-provider")
	public void isArray(Object array) {
		want.bool(ArrayUtil.isArray(array)).is(true);

	}

	public void isArray_False() {
		want.bool(ArrayUtil.isArray(null)).is(false);
		want.bool(ArrayUtil.isArray(1)).is(false);
	}

	@Test(dataProvider = "coll-provider")
	public void convert(Collection<?> coll) {
		want.bool(ArrayUtil.isArray(ArrayUtil.convert(coll))).is(true);
	}

	@Test(dataProvider = "coll-provider")
	public void isCollection(Collection<?> coll) {
		want.bool(ArrayUtil.isCollection(coll)).is(true);
	}

	public void isCollection_False() {
		want.bool(ArrayUtil.isCollection(null)).is(false);
		want.bool(ArrayUtil.isCollection(true)).is(false);
		want.bool(ArrayUtil.isCollection(Integer.valueOf(1))).is(false);
	}

	@Test(dataProvider = "coll-provider")
	public void sizeOf_collection(Object coll) {
		want.number(ArrayUtil.sizeOf(coll)).isEqualTo(2);
	}

	@Test(dataProvider = "array-provider")
	public void sizeOf_array(Object array) {
		want.number(ArrayUtil.sizeOf(array)).isEqualTo(2);
	}

	public void sizeOf_One() {
		want.number(ArrayUtil.sizeOf(null)).isEqualTo(0);
		want.number(ArrayUtil.sizeOf(1)).isEqualTo(1);
	}

	@DataProvider(name = "coll-provider")
	public Object[][] collProvider() {
		return new Object[][] { { Arrays.asList('a', 'b') }, /** <br> */
		{ Arrays.asList(true, false) }, /** <br> */
		{ Arrays.asList(1, 2) }, /** <br> */
		{ Arrays.asList(1L, 2L) }, /** <br> */
		{ Arrays.asList(null, null) }, /** <br> */
		{ Arrays.asList(1f, 2f) } };
	}

	@DataProvider(name = "array-provider")
	public Object[][] arrayProvider() {
		return new Object[][] { { new char[] { 'a', 'b' } }, /** <br> */
		{ new boolean[] { true, false } }, /** <br> */
		{ new byte[] { Byte.MAX_VALUE, Byte.MIN_VALUE } }, /** <br> */
		{ new short[] { 1, 2 } }, /** <br> */
		{ new int[] { 1, 2 } }, /** <br> */
		{ new long[] { 1L, 2L } }, /** <br> */
		{ new float[] { 1f, 2f } }, /** <br> */
		{ new double[] { 1d, 2d } }, /** <br> */
		{ new Object[] { null, null } } };
	}
}
