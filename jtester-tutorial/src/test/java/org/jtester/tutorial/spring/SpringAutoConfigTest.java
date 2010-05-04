package org.jtester.tutorial.spring;

import org.jtester.testng.JTester;
import org.jtester.tutorial.beans.PhoneItem;
import org.jtester.tutorial.services.PhoneBookService;
import org.jtester.unitils.dbfit.DbFit;
import org.jtester.unitils.spring.AutoBeanInject;
import org.jtester.unitils.spring.AutoBeanInject.BeanMap;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test
@SpringApplicationContext( { "spring/data-source.xml" })
@AutoBeanInject(maps = { @BeanMap(intf = "**.*", impl = "**.impl.*Impl") })
public class SpringAutoConfigTest extends JTester {
	@SpringBeanByName
	PhoneBookService phoneBookService;

	@DbFit(when = "testFindPhoneItemByName.when.wiki")
	public void testFindPhoneItemByName() {
		PhoneItem item = phoneBookService.findPhoneByName("darui.wu");
		want.object(item).notNull().propertyEq("mobile", "159900001111");
	}
}
