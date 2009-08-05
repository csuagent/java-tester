package org.jtester.dbfit;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class JTesterRunnerTest extends JTester {
	@Test
	public void test() throws Exception {
		JTesterRunner tdd = new JTesterRunner("test-output");

		tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery", "org/jtester/dbfit/fixture/SimpleQuery.wiki");
	}

	@Test
	public void test2() throws Exception {
		JTesterRunner.run("org/jtester/dbfit/fixture/SimpleQuery.wiki");
	}
}
