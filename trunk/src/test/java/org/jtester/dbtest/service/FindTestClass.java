package org.jtester.dbtest.service;

import java.util.List;

import org.jtester.testng.JTester;
import org.jtester.utility.FindClazUtil;
import org.jtester.utility.FindTestUtil;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class FindTestClass extends JTester {
	@Test
	public void test0() {
		System.out.println("================================");
		String packagename = FindClazUtil.class.getPackage().getName();

		List<String> clazz = FindClazUtil.findClazz(packagename);
		for (String classname : clazz) {
			System.out.println(classname);
		}
		System.out.println("================================");
	}

	@Test
	public void test1() {
		System.out.println("================================");
		Class<?> claz = UserService.class;
		List<String> clazz = FindClazUtil.findClazz(claz);

		for (String classname : clazz) {
			System.out.println(classname);
		}
		System.out.println("================================");
	}

	@Test
	public void test2() {
		System.out.println("================================");
		Class<?> claz = UserService.class;
		List<String> clazz = FindTestUtil.findTestClaz(claz);

		for (String classname : clazz) {
			System.out.println(classname);
		}
		System.out.println("================================");
	}

	@Test
	public void test3() {
		System.out.println("================================");
		List<String> clazz = FindClazUtil.findClazz(junit.extensions.ActiveTestSuite.class);

		for (String classname : clazz) {
			System.out.println(classname);
		}
		System.out.println("================================");
	}
}
