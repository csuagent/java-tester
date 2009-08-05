package org.jtester.dbfit;

import java.io.IOException;

import org.jtester.junit4.JTester;
import org.junit.Test;

import fit.Counts;

public class JTesterRunnerTest extends JTester {
	@Test
	public void test() throws IOException {
		JTesterRunner tdd = new JTesterRunner("./test-output");

		// Counts cs = tdd.runSuite("AcceptanceTests.JavaTests.SimpleQuery");
		Counts cs = tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery", "org/jtester/dbfit/fixture/SimpleQuery.wiki");
		System.err.println("Total right=" + cs.right + "; wrong=" + cs.wrong + " exceptions=" + cs.exceptions);
	}
}
