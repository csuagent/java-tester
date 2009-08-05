package org.jtester.dbfit;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "jtester" })
public class JTesterRunnerTest extends JTester {
	@Test
	public void runTest_HasName() throws Exception {
		JTesterRunner tdd = new JTesterRunner("test-output");

		tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery", "org/jtester/dbfit/fixture/SimpleQuery.wiki");
	}

	@Test
	public void runTest() throws Exception {
		JTesterRunner.run("org/jtester/dbfit/fixture/SimpleQuery.wiki");
	}
}
