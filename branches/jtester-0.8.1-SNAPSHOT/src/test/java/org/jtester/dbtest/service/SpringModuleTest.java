package org.jtester.dbtest.service;

import org.jtester.fortest.hibernate.AddressService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext( { "file:./extern-spring/project.xml" })
@Test(groups = { "JTester" })
public class SpringModuleTest extends JTester {
	@SpringBean("addressService")
	private AddressService addressService;

	@Test
	public void testInitDb() {
		want.object(addressService).notNull();
	}
}
