package org.jtester.dbfit;

import org.jtester.junit4.JTester;
import org.junit.Test;

public class JTesterRunnerTest extends JTester {
	@Test
	public void test() throws Exception {
		JTesterRunner tdd = new JTesterRunner("test-output");

		tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery", "org/jtester/dbfit/fixture/SimpleQuery.wiki");

	}
}
