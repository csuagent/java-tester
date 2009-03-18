package org.jtester.dbtest.service;

import org.jtester.testng.JTester;
import org.jtester.unitils.inject.InjectedMock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
@Test(groups = { "JTester" })
public class UserServiceTest_ByInjectedMock extends JTester {
	@SpringBean("userService")
	@TestedObject
	private UserService userService;

	@InjectedMock
	private AddressService addressService;

	@Test
	public void test01() {
		want.object(addressService).notNull();
		want.object(userService).notNull();
		checking(new Je() {
			{
				$.call.one(addressService).findAddress();
				$.will.returnValue("文二路120#");
			}
		});
		String address = userService.findAddress();
		want.string(address).contains("120#");
	}
}
