package org.jtester.dbtest.test;

//import org.jtester.dbtest.bean.User;
//import org.jtester.dbtest.service.UserService;
//import org.jtester.testng.JTester;
//import org.testng.annotations.Test;
//import org.unitils.spring.annotation.SpringApplicationContext;
//import org.unitils.spring.annotation.SpringBeanByType;

//@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project.xml" })
//@Test(groups = { "JTester" })
//public class GroovyPrepareData extends JTester {
//	@SpringBeanByType
//	private UserService userService;
//
//	@Test
//	public void testFindUser() {
//		PrepareData.prepare(userService.session());
//
//		User user = userService.getBeanById(1);
//		want.object(user).notNull();
//		want.number(user.getAddresses().size()).isEqualTo(3);
//	}
//}
