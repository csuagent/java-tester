package org.jtester.tutorial.spring;

import org.jtester.testng.JTester;
import org.jtester.tutorial.beans.PhoneItem;
import org.jtester.tutorial.daos.PhoneItemDao;
import org.jtester.unitils.dbfit.DbFit;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test
@SpringApplicationContext( { "spring/data-source.xml", "spring/beans.xml" })
public class SpringIntegratedTest extends JTester {
	@SpringBeanByName
	private PhoneItemDao phoneItemDao;

	@DbFit(when = "testFindPhoneItemByName.when.wiki")
	public void testFindPhoneItemByName() {
		PhoneItem item = phoneItemDao.findPhoneByName("darui.wu");
		want.object(item).notNull().propertyEq("mobile", "159900001111");
	}
}
