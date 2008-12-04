package org.jtester.dbtest.test;

import org.jtester.dbtest.bean.User;
import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
@Test(groups = { "JTester" })
public class TestUserService extends JTester {
	@SpringBeanByType
	private UserService userService;

	@Test
	public void testFindUser() {
		User user = userService.getUser(1);
		want.object(user).isNull();
	}
}
