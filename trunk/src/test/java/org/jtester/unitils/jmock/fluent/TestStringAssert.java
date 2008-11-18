package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestStringAssert extends JTester {
	@Test
	public void test1() {
		// assertThat("ddd").containsString("a").or().containsString("d").match();
		//		
		// assertThat("ddd").eq("ddd");
		// assertThat("ddd").eqIgnoreCase("dDD");
		//		
		// assertThat("eeeed").endWith("ed");

		assertThat("eeeed").end("ed").start("ed");
	}
}
