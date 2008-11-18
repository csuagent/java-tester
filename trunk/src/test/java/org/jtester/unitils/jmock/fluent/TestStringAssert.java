package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestStringAssert extends JTester {
	@Test
	public void test1() {
		assertThat("ddd").contains("d").contains("d");

		assertThat("ddd").eq("ddd");
		assertThat("ddd").eqIgnoreCase("dDD");

		assertThat("eeeed").end("ed");

		assertThat("eeeed").end("ed").start("eee");

	}
}
