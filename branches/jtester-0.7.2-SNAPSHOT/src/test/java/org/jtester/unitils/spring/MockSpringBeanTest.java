package org.jtester.unitils.spring;

import java.util.ArrayList;

import org.jtester.testng.JTester;
import org.jtester.unitils.database.ibatis.beans.User;
import org.jtester.unitils.database.ibatis.service.UserDao;
import org.jtester.unitils.database.ibatis.service.UserService;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@Test(groups = "jtester")
@SpringApplicationContext( { "org/jtester/unitils/database/ibatis/spring/beans.xml",
		"org/jtester/unitils/database/ibatis/spring/data-source.xml" })
public class MockSpringBeanTest extends JTester {
	@SpringBeanByName
	@TestedObject
	private UserService userService;

	@Mock
	@InjectIntoByType(target = "userService")
	private UserDao userDao;

	public void paySalary() {
		checking(new Je() {
			{
				will.call.one(userDao).findUserByPostcode("310000");
				will.returns.value(new ArrayList<User>() {
					private static final long serialVersionUID = -2799578129563837839L;
					{
						this.add(new User(1, 1000d));
						this.add(new User(2, 1500d));
						this.add(new User(2, 1800d));
					}
				});
			}
		});

		double total = this.userService.paySalary("310000");
		want.number(total).isEqualTo(4300d);
	}
}
