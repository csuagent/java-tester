package org.jtester.unitils.spring;

import java.util.ArrayList;

import org.jtester.beans.User;
import org.jtester.unitils.database.ibatis.service.UserDao;
import org.jtester.unitils.database.ibatis.service.UserService;
import org.jtester.unitils.jmock.MockBean;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByName;

@Test(groups = { "jtester", "mockbean" })
public class MockBeanByNameTest_Mock extends MockBeanByNameTest_Base {
	@SpringBeanByName
	private UserService userService;

	@MockBean
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
