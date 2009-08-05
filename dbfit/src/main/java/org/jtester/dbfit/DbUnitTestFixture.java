package org.jtester.dbfit;

import org.jtester.testng.TestNgUtil;

public class DbUnitTestFixture extends DatabaseFixture {
	public boolean testng(String clazz, String method) {
		return TestNgUtil.run(clazz, method, false);
	}
}
