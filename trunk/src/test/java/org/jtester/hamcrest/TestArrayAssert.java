package org.jtester.hamcrest;

import java.util.Arrays;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestArrayAssert extends JTester {
	@Test
	public void test1() {
		want.array(new String[] { "aaaa", "bbbb" }).hasItems("aaaa", "bbbb");
		want.array(new int[] { 1, 2 }).hasItems(1);
		want.array(new double[] { 1, 2.0d }).hasItems(1d);

		want.array(new int[] { 1, 2, 3 }).hasItems(Arrays.asList(1, 3));
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test2() {
		want.array(new int[] { 1, 2, 3 }).hasItems(Arrays.asList(1, 4));
	}
}
