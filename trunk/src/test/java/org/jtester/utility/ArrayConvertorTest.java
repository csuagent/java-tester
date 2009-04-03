package org.jtester.utility;

import org.jtester.testng.JTester;
import org.jtester.utility.ArrayConvertor;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ArrayConvertorTest extends JTester {
	@Test
	public void convert_charArr() {
		char[] chs = { 'a', 'b' };
		want.array(chs).sizeIs(2);
		Object[] os = ArrayConvertor.convert(chs);
		want.array(os).sizeIs(2);
		want.array(os).hasItems('a', 'b');
	}

	@Test
	public void convert_booleanArr() {
		boolean[] bls = { true, false, true };
		want.array(bls).sizeIs(3);
		Object[] os = ArrayConvertor.convert(bls);
		want.array(os).sizeIs(3);
		want.array(os).hasItems(true);
	}

	@Test
	public void convert_byteArr() {
		byte[] bytes = { Byte.MAX_VALUE, Byte.MIN_VALUE };
		want.array(bytes).sizeIs(2);
		Object[] os = ArrayConvertor.convert(bytes);
		want.array(os).sizeIs(2);
		want.array(os).hasItems(Byte.MAX_VALUE, Byte.MIN_VALUE);
	}

	@Test
	public void convert_shortArr() {
		short[] shorts = { 2, 4, 5 };
		want.array(shorts).sizeIs(3);
		Object[] os = ArrayConvertor.convert(shorts);
		want.array(os).sizeIs(3);
		want.array(os).hasItems((short)2);
		
		//want.number((short)5).isEqualTo(5);
	}

	@Test
	public void convert_intArr() {
		int[] ints = { 2, 4, 5 };
		want.array(ints).sizeIs(3);
		Object[] os = ArrayConvertor.convert(ints);
		want.array(os).sizeIs(3);
		want.array(os).hasItems(4);
	}

	@Test
	public void convert_longArr() {
		long[] longs = { 2L, 421355L, 51255L };
		want.array(longs).sizeIs(3);
		Object[] os = ArrayConvertor.convert(longs);
		want.array(os).sizeIs(3);
		want.array(os).hasItems(51255L);
	}

	@Test
	public void convert_floatArr() {
		float[] fs = { 2.0f, 4.0f, 5.1f };
		want.array(fs).sizeIs(3);
		Object[] os = ArrayConvertor.convert(fs);
		want.array(os).sizeIs(3);
		want.array(os).hasItems(5.1f);
	}

	@Test
	public void convert_doubleArr() {
		double[] ds = { 2.0d, 4.0d, 5.1d };
		want.array(ds).sizeIs(3);
		Object[] os = ArrayConvertor.convert(ds);
		want.array(os).sizeIs(3);
		want.array(os).hasItems(5.1d);
	}
}
