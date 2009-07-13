package org.jtester.unitils.database.ibatis;

import org.jtester.testng.JTester;
import org.jtester.unitils.database.ibatis.beans.User;
import org.jtester.unitils.database.ibatis.service.UserService;
import org.jtester.unitils.dbwiki.WikiDataSet;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.datasetloadstrategy.impl.RefreshLoadStrategy;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test(groups = { "JTester" })
@SpringApplicationContext( { "classpath:org/jtester/unitils/database/ibatis/spring/beans.xml",
		"classpath:org/jtester/unitils/database/ibatis/spring/data-source.xml" })
public class WikiDataSetTest extends JTester {
	@SpringBeanByName
	private UserService userService;

	@DataSet( { "DbTester.paySalary.xml" })
	public void paySalary() {
		double total = this.userService.paySalary("310000");
		want.number(total).isEqualTo(4000d);
	}

	@WikiDataSet(value = { "DbTester.paySalary.wiki" }, loadStrategy = RefreshLoadStrategy.class)
	public void paySalary_wiki() {
		double total = this.userService.paySalary("310000");
		want.number(total).isEqualTo(4000d);
	}

	@WikiDataSet(value = { "DbTester.paySalary.wiki" }, loadStrategy = RefreshLoadStrategy.class)
	public void paySalary_insert() {
		User user = new User();
//		user.setId(5);
		user.setFirst("first name");
		user.setPostcode("320001");
		user.setSarary(23.02d);
		this.userService.insertUser(user);
		// want.number(total).isEqualTo(4000d);
	}
}
