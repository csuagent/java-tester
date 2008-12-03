package org.jtester.dbtest.test;

import org.jtester.dbtest.service.AddressService;
import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.jtester.unitils.jmock.Mock;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
@Test(groups = { "JTester" })
public class TestService extends JTester {
	@SpringBean("userService")
	@TestedObject
	private UserService userService;

	@Mock
	@InjectIntoByType
	private AddressService addressService;

	@Test
	public void test01() {
		want.object(addressService).notNull();
		want.object(userService).notNull();
	}
}
