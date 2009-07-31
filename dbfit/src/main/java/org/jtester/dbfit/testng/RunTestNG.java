package org.jtester.dbfit.testng;

import org.jtester.dbfit.DatabaseFixture;

public class RunTestNG extends DatabaseFixture implements ITestNgRun {
	public boolean testng(String clazz, String method) {
		return TestNgUtil.testng(clazz, method);
	}
}
