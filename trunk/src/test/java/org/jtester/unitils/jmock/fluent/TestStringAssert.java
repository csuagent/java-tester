package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestStringAssert extends JTester {
	// @Test
	public void test1() {
		want("ddd").contains("d").contains("d");

		want("ddd").eq("ddd");
		want("ddd").eqIgnoreCase("dDD");

		want("eeeed").end("ed");

		want("eeeed").end("ed").start("eee");

	}

	@Test
	public void test2() {
		want("abcd").or(the().string.contains("ad"), the().string.contains("de"));
	}

	@Test
	public void test3() {
		want("abcd").or(the().string.contains("ab"), the().string.contains("cd"));
	}
}
