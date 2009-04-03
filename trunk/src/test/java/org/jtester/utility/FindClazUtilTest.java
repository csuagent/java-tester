package org.jtester.utility;

import java.util.List;

import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.jtester.utility.FindClazUtil;
import org.jtester.utility.FindMethodUtil;
import org.testng.annotations.Test;

/**
 * this test will break for EclEmma Test
 * 
 * @author darui.wudr
 * 
 */
@Test(groups = { "JTester" })
public class FindClazUtilTest extends JTester {
	@Test
	public void findClazz_1() {
		String packagename = FindClazUtil.class.getPackage().getName();
		want.string(packagename).isEqualTo("org.jtester.utility");
		List<String> clazz = FindClazUtil.findClazz(packagename);
		want.collection(clazz).sizeGe(9);
		// for (String classname : clazz) {
		// System.out.println(classname);
		// }
		// System.out.println("================================");
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
		List<String> clazz = FindMethodUtil.findTestClaz(claz);

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
