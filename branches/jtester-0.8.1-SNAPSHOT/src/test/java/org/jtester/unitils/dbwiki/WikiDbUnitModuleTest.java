package org.jtester.unitils.dbwiki;

import java.util.Collection;

import org.jtester.fortest.hibernate.Address;
import org.jtester.fortest.hibernate.User;
import org.jtester.fortest.hibernate.UserService;
import org.jtester.testng.JTester;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.unitils.core.Unitils;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@Test(groups = { "JTester" })
@SpringApplicationContext( { "classpath:/org/jtester/fortest/hibernate/project.xml" })
public class WikiDbUnitModuleTest extends JTester {
	@SpringBeanByType
	private UserService userService;

	@WikiDataSet("get user.wiki")
	@WikiExpectedDataSet("get user.expected.wiki")
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

	@WikiDataSet("empty.wiki")
	@Test(dependsOnMethods = { "getUser" })
	public void cleanData() {

	}

	@Test(dependsOnMethods = { "cleanData" })
	@WikiExpectedDataSet("empty.wiki")
	public void expectedDataSet() {
		User user1 = userService.getUser(1);
		want.object(user1).isNull();
	}

	@WikiDataSet( { "lazy address.wiki" })
	public void getUser_LazyAddress() {
		User user = userService.getUser(1);
		want.object(user).notNull();

		want.object(user.getAddresses()).notNull();
		Collection<Address> addresses = user.getAddresses();
		want.object(addresses.size()).isEqualTo(1);
		for (Address address : addresses) {
			want.string(address.getCity()).contains("city");
		}
	}

	private WikiDbUnitModule module = null;

	@BeforeClass
	public void setup() {
		module = new WikiDbUnitModule();
		module.init(Unitils.getInstance().getConfiguration());
	}

	@Test
	public void getDataSet() throws SecurityException, NoSuchMethodException {
		MultiSchemaDataSet dataSet = module.getDataSet(this.getClass().getMethod("getUser"), this);
		want.object(dataSet).notNull();
	}

	@Test
	public void getDefaultDataSetFactory() {
		DataSetFactory factory = module.getDefaultDataSetFactory();
		want.object(factory).clazIs(MultiSchemaWikiDataSetFactory.class);
	}
}
