package org.jtester.unitils.database.ibatis;

import org.jtester.testng.JTester;
import org.jtester.unitils.database.ibatis.beans.User;
import org.jtester.unitils.database.ibatis.service.UserService;
import org.jtester.unitils.dbfit.DbFit;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test(groups = { "JTester" })
@SpringApplicationContext( { "classpath:org/jtester/unitils/database/ibatis/spring/beans.xml",
		"classpath:org/jtester/unitils/database/ibatis/spring/data-source.xml" })
public class DbFitTest extends JTester {
	@SpringBeanByName
	private UserService userService;

	@DbFit(when = { "DbFit.paySalary_insert.when.wiki" }, then = "DbFit.paySalary_insert.then.wiki")
	public void paySalary_insert() {
		User user = new User();
		user.setFirst("first name");
		user.setPostcode("320001");
		user.setSarary(23.02d);
		this.userService.insertUser(user);
	}
}
