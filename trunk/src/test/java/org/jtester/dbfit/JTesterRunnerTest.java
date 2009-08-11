package org.jtester.dbfit;

import org.jtester.testng.JTester;
import org.jtester.unitils.dbfit.DbFit;
import org.testng.annotations.Test;

@Test(groups = { "jtester" })
public class JTesterRunnerTest extends JTester {
	@Test
	@DbFit(when = "org/jtester/dbfit/SimpleQuery_Init.wiki")
	public void runTest_HasName() throws Exception {
		JTesterRunner tdd = new JTesterRunner("test-output");

		tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery", JTesterRunnerTest.class, "SimpleQuery.wiki");
	}

	@Test
	@DbFit(when = "org/jtester/dbfit/SimpleQuery_Init.wiki")
	public void runTest() throws Exception {
		JTesterRunner.run(null, "org/jtester/dbfit/SimpleQuery.wiki");
	}
}
