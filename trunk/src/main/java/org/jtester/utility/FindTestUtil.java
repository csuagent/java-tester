package org.jtester.utility;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 获得一个类（方法）的测试类（方法）的工具
 * 
 * @author darui.wudr
 * 
 */
public class FindTestUtil {
	/**
	 * 获得claz的所有测试类
	 * 
	 * @param claz
	 * @return
	 */
	public static List<String> findTestClaz(Class<?> claz) {
		List<String> clazz = FindClazUtil.findClazz(claz);
		String classname = claz.getName();
		List<String> tests = FindTestUtil.filterClaz(clazz, classname);

		return tests;
	}

	/**
	 * 获得claz的所有测试类
	 * 
	 * @param claz
	 * @return
	 */
	public static List<String> findTestClaz(String claz) {
		List<String> clazz = FindClazUtil.findClazz(claz);
		String classname = claz.substring(claz.lastIndexOf("."));
		List<String> tests = FindTestUtil.filterClaz(clazz, classname);

		return tests;
	}

	private static List<String> filterClaz(List<String> clazz, String classname) {
		List<String> tests = new LinkedList<String>();
		if (clazz == null) {
			return tests;
		}
		for (String _claz : clazz) {
			if (_claz.contains("$") || _claz.equals(classname)) {
				continue;
			}
			if (_claz.contains(classname)) {
				tests.add(_claz);
			}
		}
		return tests;
	}

	/**
	 * 获得method的所有测试方法
	 * 
	 * @param claz
	 * @param method
	 * @return
	 */
	public static List<String> findTestMethod(Class<?> claz, Method method) {
		return null;// TODO
	}

	/**
	 * 获得method的所有测试方法
	 * 
	 * @param claz
	 * @param method
	 * @return
	 */
	public static List<String> findTestMethod(Class<?> claz, String method) {
		return null;// TODO
	}
}
