package org.jtester.hamcrest;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestIntegerAssert extends JTester {
	@Test
	public void test1() {
		want.integer(3).between(2, 5);
		want.integer(3).geq(3);
		want.integer(3).gt(2);
		want.integer(3).leq(3);
		want.integer(3).lt(4);
		want.integer(3).eq(3);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test2() {
		want.integer(3).between(5, 2);
	}
}
