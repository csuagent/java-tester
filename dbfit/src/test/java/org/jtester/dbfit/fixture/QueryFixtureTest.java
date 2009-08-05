package org.jtester.dbfit.fixture;

import org.jtester.dbfit.DbFixtureTest;
import org.junit.Test;

public class QueryFixtureTest extends DbFixtureTest {
	@Test
	public void ttt() {
		run("AcceptanceTests.JavaTests.MySqlTests.ConnectUsingFile.SimpleSelect");
	}
}
