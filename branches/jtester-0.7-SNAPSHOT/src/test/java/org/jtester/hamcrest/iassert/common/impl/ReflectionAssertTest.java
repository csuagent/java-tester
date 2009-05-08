package org.jtester.hamcrest.iassert.common.impl;

import org.jtester.beans.Manager;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ReflectionAssertTest extends JTester {
	public void propertyMatch() {
		Manager manager = new Manager();
		manager.setName("I am darui.wu");
		want.object(manager).propertyMatch("name", the.string().contains("darui"));
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void propertyMatch_AssertFail() {
		Manager manager = new Manager();
		manager.setName("I am darui.wu");
		want.object(manager).propertyMatch("name", the.string().contains("darui1"));
	}
}
