package org.jtester.tutorial.integrated;

import org.jtester.fit.fixture.DtoPropertyFixture;
import org.jtester.tutorial01.beans.PhoneItem;
import org.jtester.tutorial01.daos.PhoneItemDao;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@SpringApplicationContext( { "spring/beans.xml", "spring/data-source.xml" })
public class SpringUsageFixture extends DtoPropertyFixture {
	@SpringBeanByName
	private PhoneItemDao phoneItemDao;

	public void insertUser(PhoneItem item) {
		phoneItemDao.insertPhoneItem(item);
	}
}
