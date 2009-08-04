package org.jtester.unitils.spring;

import org.jtester.unitils.database.ibatis.service.UserService;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByName;

@Test(groups = { "jtester", "mockbean" })
public class MockBeanByNameTest_NoMock extends MockBeanByNameTest_Base {
	@SpringBeanByName
	private UserService userService;

	public void paySalary() {
		double total = this.userService.paySalary("310000");
		want.number(total).isEqualTo(4000d);
	}
}
