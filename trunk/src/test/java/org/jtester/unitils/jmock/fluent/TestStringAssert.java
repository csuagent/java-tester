package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestStringAssert extends JTester {
	// @Test
	public void test1() {
		want.string("ddd").contains("d").contains("d");

		want.string("ddd").eq("ddd");
		want.string("ddd").eqIgnoreCase("dDD");

		want.string("eeeed").end("ed");

		want.string("eeeed").end("ed").start("eee");

	}

	@Test
	public void test2() {
		want.string("abcd").anyOf(the().string.contains("ad"), the().string.contains("de"));
	}

	@Test
	public void test3() {
		want.string("abcd").anyOf(the().string.contains("ab"), the().string.contains("cd"));
	}
}
