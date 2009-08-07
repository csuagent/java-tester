package org.jtester.unitils.dbfit;

import org.jtester.dbtest.bean.User;
import org.jtester.fortest.hibernate.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@Test(groups = { "JTester" })
@SpringApplicationContext( { "classpath:/org/jtester/fortest/hibernate/project.xml" })
public class DbFitModuleTest extends JTester {
	@SpringBeanByType
	private UserService userService;

	@DbFit(when = "org/jtester/unitils/dbfit/DbFitModuleTest.getUser.wiki")
	public void getUser() {
		User user1 = userService.getUser(1);
		want.object(user1).notNull();
		User user2 = userService.getUser(2);
		want.object(user2).notNull();

		User user3 = userService.getUser(3);
		want.object(user3).isNull();
		User user4 = userService.getUser(4);
		want.object(user4).isNull();
	}
}
