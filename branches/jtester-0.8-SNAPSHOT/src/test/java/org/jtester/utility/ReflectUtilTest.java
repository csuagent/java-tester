package org.jtester.utility;

import org.jtester.exception.JTesterException;
import org.jtester.fortest.beans.Employee;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ReflectUtilTest extends JTester {

	public void setFieldValue() {
		Employee employee = new Employee();
		want.object(employee.getName()).isNull();
		ReflectUtil.setFieldValue(employee, "name", "my name");
		want.object(employee).propertyEq("name", "my name");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void setFieldValue_exception() {
		Employee employee = new Employee();
		want.object(employee.getName()).isNull();
		ReflectUtil.setFieldValue(employee, "name1", "my name");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void setFieldValue_AssertError() {
		ReflectUtil.setFieldValue(null, "name1", "my name");
	}

	public void getFieldValue() {
		Employee employee = new Employee();
		employee.setName("test name");
		Object name = ReflectUtil.getFieldValue(employee, "name");
		want.object(name).clazIs(String.class);
		want.string(name.toString()).isEqualTo("test name");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void getFieldValue_exception() {
		Employee employee = new Employee();
		employee.setName("test name");
		ReflectUtil.getFieldValue(employee, "name1");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void getFieldValue_AssertError() {
		ReflectUtil.getFieldValue(null, "name1");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void getFieldValue_AssertError2() {
		ReflectUtil.getFieldValue(Employee.class, null, "name1");
	}
}
