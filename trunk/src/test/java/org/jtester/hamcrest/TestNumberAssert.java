package org.jtester.hamcrest;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestNumberAssert extends JTester {
	@Test
	public void test1() {
		want.number(3).between(2, 5);
		want.number(3).geq(3);
		want.number(3).gt(2);
		want.number(3).leq(3);
		want.number(3).lt(4);
		want.number(3).eq(3);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test2() {
		want.number(3).between(5, 2);
	}
}
