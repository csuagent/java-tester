package org.jtester.tutorial.debugit;

import java.util.List;

import org.jtester.testng.JTester;
import org.jtester.tutorial.beans.PhoneItem;
import org.jtester.tutorial.services.PhoneBookService;
import org.jtester.unitils.dbfit.DbFit;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test
@SpringApplicationContext( { "spring/data-source.xml", "spring/beans.xml" })
public class JTesterDemo extends JTester {
	@SpringBeanByName
	PhoneBookService phoneBookService;

	@DbFit(when = "testFindPhoneItemsByGroupName.wiki")
	public void testFindPhoneItemsByGroupName() {
		List<PhoneItem> items = phoneBookService.findPhoneItemsByGroupName("classmate");
		want.collection(items).sizeEq(2).propertyCollectionRefEq(new String[] { "username", "mobile" },
				new String[][] { { "darui.wu", "15900001111" }, { "jobs.he", "13900001111" } });
	}
}
