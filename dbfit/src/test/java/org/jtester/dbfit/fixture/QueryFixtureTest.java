package org.jtester.dbfit.fixture;

import org.jtester.dbfit.DbFixtureTest;
import org.junit.Test;

import com.neuri.trinidad.TestRunner;
import com.neuri.trinidad.fitnesserunner.FitLibraryTestEngine;
import com.neuri.trinidad.fitnesserunner.FitNesseRepository;

import fit.Counts;

public class QueryFixtureTest extends DbFixtureTest {
	@Test
	public void simpleQuery() {
		run("AcceptanceTests.JavaTests.MySqlTests.ConnectUsingFile.SimpleSelect");
	}

	@Test
	public void simpleQuery2() {
		run("AcceptanceTests.JavaTests.SimpleQuery");
	}

	public static void main(String[] args) throws Exception {
		TestRunner tdd = new TestRunner(new FitNesseRepository("."), new FitLibraryTestEngine(), "d:/fitnesse");

		// Counts cs = tdd.runSuite("AcceptanceTests.JavaTests.SimpleQuery");
		Counts cs = tdd.runTest("AcceptanceTests.JavaTests.SimpleQuery");
		System.err.println("Total right=" + cs.right + "; wrong=" + cs.wrong + " exceptions=" + cs.exceptions);
	}
}
