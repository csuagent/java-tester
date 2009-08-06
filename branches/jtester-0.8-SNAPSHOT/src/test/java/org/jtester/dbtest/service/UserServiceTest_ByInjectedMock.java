package org.jtester.dbtest.service;

import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
@Test(groups = { "JTester" })
public class UserServiceTest_ByInjectedMock extends JTester {
	@SpringBean("userService")
	// @TestedObject
	private UserService userService;

	// @InjectedMock
	@Mock(injectInto = "userService")
	private AddressService addressService;

	@Test
	public void findAddress() {
		want.object(addressService).notNull();
		want.object(userService).notNull();
		checking(new Je() {
			{
				will.call.one(addressService).findAddress();
				will.returns.value("文二路120#");

				// will.return_value("").when_call_one(addressService).findAddress();
				// want.call_one(addressService).findAddress();
			}
		});
		String address = userService.findAddress();
		want.string(address).contains("120#");
	}
}
