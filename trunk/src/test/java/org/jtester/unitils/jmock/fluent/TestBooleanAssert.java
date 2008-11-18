package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestBooleanAssert extends JTester {

	@Test
	public void test1() {
		want.bool(true).eq(true);
		want.bool(true).is(true);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test2() {
		want.bool(true).is(false);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test3() {
		fail();
	}
}
