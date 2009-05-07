package org.jtester.unitils.dbwiki;

import org.jtester.dbtest.bean.User;
import org.jtester.dbtest.service.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@Test(groups = { "JTester" })
@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
public class WikiDbUnitModuleTest extends JTester {
	@SpringBeanByType
	private UserService userService;

	@Test
	// @DataSet( { "UserServiceTest.getUser.xml" })
	@WikiDataSet("getUser.wiki")
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

	// @Test
	// @DataSet( { "UserServiceTest.getUser_LazyAddress.xml" })
	// public void getUser_LazyAddress() {
	// User user = userService.getUser(1);
	// want.object(user).notNull();
	//
	// want.object(user.getAddresses()).notNull();
	// Collection<Address> addresses = user.getAddresses();
	// want.object(addresses.size()).isEqualTo(1);
	// for (Address address : addresses) {
	// want.string(address.getCity()).contains("city");
	// }
	// }
}
