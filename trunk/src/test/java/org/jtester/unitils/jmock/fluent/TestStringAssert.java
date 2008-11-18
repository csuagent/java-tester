package org.jtester.unitils.jmock.fluent;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestStringAssert extends JTester {
	@Test
	public void test1() {
		assertThat("ddd").containsString("a").or().containsString("d").match();
	}
}
