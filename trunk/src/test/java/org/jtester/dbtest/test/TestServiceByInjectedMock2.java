package org.jtester.dbtest.test;

import org.jtester.dbtest.service.AddressService;
import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.jtester.unitils.inject.InjectedMock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
@Test(groups = { "JTester" })
public class TestServiceByInjectedMock2 extends JTester {
	@SpringBean("userService")
	@TestedObject
	private UserService userService1;

	@InjectedMock(target = "userService1", property = "addressService", value = "addressService1")
	private AddressService addressService1;

	@Test
	public void test012() {
		want.object(addressService1).notNull();
		want.object(userService1).notNull();
		checking(new Je() {
			{
				$.call.one(addressService1).findAddress();
				$.will.returnValue("文三路131#");
			}
		});
		String address = userService1.findAddress();
		want.string(address).contains("131#");
	}
}
