package org.jtester.utility;

import java.util.List;

import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class FindMethodUtilTest extends JTester {
	@Test
	public void test1() {
		System.out.println("================================");
		List<String> methods = FindMethodUtil.findTestMethod(UserService.class, "findAddress");
		for (String method : methods) {
			System.out.println(method);
		}
		System.out.println("================================");
	}

	@Test
	public void test2() {
		System.out.println("================================");
		List<String> methods = FindMethodUtil.findTestMethod(UserService.class, "getUser");
		for (String method : methods) {
			System.out.println(method);
		}
		System.out.println("================================");
	}
}
