package org.jtester.utility;

import org.jtester.testng.JTester;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ClazzUtilTest extends JTester {

	@Test(dataProvider = "provideClazzName")
	public void getPackFromClassName(String clazz, String pack) {
		want.string(ClazzUtil.getPackFromClassName(clazz)).isEqualTo(pack);
	}

	@DataProvider
	public Object[][] provideClazzName() {
		return new String[][] { { "", "" }, { "EefErr", "" },
				{ "org.jtester.utility.ClazzUtilTest", "org.jtester.utility" } };
	}
}
