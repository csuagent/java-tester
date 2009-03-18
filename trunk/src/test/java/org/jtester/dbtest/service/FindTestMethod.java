package org.jtester.dbtest.service;

import java.util.List;

import org.jtester.utility.FindTestUtil;
import org.junit.Test;

public class FindTestMethod {
	@Test
	public void test1() {
		System.out.println("================================");
		List<String> methods = FindTestUtil.findTestMethod(UserService.class, "findAddress");
		for (String method : methods) {
			System.out.println(method);
		}
		System.out.println("================================");
	}

	@Test
	public void test2() {
		System.out.println("================================");
		List<String> methods = FindTestUtil.findTestMethod(UserService.class, "getUser");
		for (String method : methods) {
			System.out.println(method);
		}
		System.out.println("================================");
	}
}
