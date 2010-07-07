package org.jtester.tutorial01.spring;

import java.util.ArrayList;
import java.util.List;

import org.jtester.testng.JTester;
import org.jtester.tutorial01.beans.PhoneItem;
import org.jtester.tutorial01.daos.PhoneItemDao;
import org.jtester.tutorial01.daos.impl.PhoneItemDaoSpecImpl;
import org.jtester.tutorial01.services.PhoneBookService;
import org.jtester.unitils.dbfit.DbFit;
import org.jtester.unitils.spring.AutoBeanInject;
import org.jtester.unitils.spring.SpringBeanFor;
import org.jtester.unitils.spring.AutoBeanInject.BeanMap;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test
@SpringApplicationContext( { "spring/data-source.xml" })
@AutoBeanInject(maps = { @BeanMap(intf = "**.*", impl = "**.impl.*Impl") })
public class SpringAutoConfigTest_Spec extends JTester {
	@SpringBeanByName
	PhoneBookService phoneBookService;

	@SpringBeanFor(PhoneItemDaoSpecImpl.class)
	PhoneItemDao phoneItemDao;

	@SpringBeanFor
	List<PhoneItem> phoneItems = new ArrayList<PhoneItem>() {
		private static final long serialVersionUID = 1144178821256035529L;

		{
			add(new PhoneItem("matt", "11112222"));
		}
	};

	@DbFit(when = "testFindPhoneItemByName.when.wiki")
	public void testFindPhoneItemByName() {
		PhoneItem item = phoneBookService.findPhoneByName("darui.wu");
		want.object(item).notNull().propertyEq("mobile", "my mobile");

		List<PhoneItem> items = this.phoneItemDao.findPageBook(1, 1);
		want.collection(items).sizeEq(1).propertyCollectionLenientEq(new String[] { "username" },
				new String[][] { { "matt" } });
	}
}
