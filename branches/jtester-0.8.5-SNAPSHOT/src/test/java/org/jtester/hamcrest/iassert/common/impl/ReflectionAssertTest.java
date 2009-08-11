package org.jtester.hamcrest.iassert.common.impl;

import org.jtester.fortest.beans.Employee;
import org.jtester.fortest.beans.Manager;
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

	public void propertyEq() {
		Employee employee = new Employee();
		want.object(employee).propertyEq("name", null);
		employee.setName("my name");
		want.object(employee).propertyEq("name", "my name");
	}

	public void propertyMatch2() {
		Employee employee = new Employee();
		want.object(employee).propertyMatch("name", the.string().isNull());
		employee.setName("my name");
		want.object(employee).propertyMatch("name", the.string().isEqualTo("my name"));
		want.object(employee).propertyEq("name", "my name");
	}
}
