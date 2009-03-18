package org.jtester.dbtest.service;

import java.util.Iterator;
import java.util.List;

import org.jtester.utility.FindClazUtil;
import org.jtester.utility.FindTestUtil;
import org.junit.Test;

public class FindTestClass {
	@Test
	public void test0() {
		System.out.println("================================");
		String packagename = FindClazUtil.class.getPackage().getName();

		List<String> clazz = FindClazUtil.findClazz(packagename);
		if (clazz != null) {
			Iterator<String> itrClasses = clazz.iterator();
			while (itrClasses.hasNext()) {
				System.out.println(itrClasses.next().toString());
			}
		}
		System.out.println("================================");
	}
	
	@Test
	public void test1() {
		System.out.println("================================");
		Class<?> claz = UserService.class;
		List<String> clazz = FindClazUtil.findClazz(claz);

		if (clazz != null) {
			Iterator<String> itrClasses = clazz.iterator();
			while (itrClasses.hasNext()) {
				System.out.println(itrClasses.next().toString());
			}
		}
		System.out.println("================================");
	}
	
	@Test
	public void test2() {
		System.out.println("================================");
		Class<?> claz = UserService.class;
		List<String> clazz = FindTestUtil.findTestClaz(claz);

		if (clazz != null) {
			Iterator<String> itrClasses = clazz.iterator();
			while (itrClasses.hasNext()) {
				System.out.println(itrClasses.next().toString());
			}
		}
		System.out.println("================================");
	}
}
