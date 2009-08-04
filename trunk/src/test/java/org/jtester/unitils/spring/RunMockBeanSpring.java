package org.jtester.unitils.spring;

import org.jtester.testng.TestNgUtil;
import org.junit.Test;

public class RunMockBeanSpring {
	@Test
	public void run1() {
		TestNgUtil.run(MockBeanByNameTest_Mock.class.getName(), "paySalary", true);
		TestNgUtil.run(MockBeanByNameTest_NoMock.class.getName(), "paySalary", true);
	}

	@Test
	public void run2() {
		TestNgUtil.run(MockBeanByNameTest_NoMock.class.getName(), "paySalary", true);
		TestNgUtil.run(MockBeanByNameTest_Mock.class.getName(), "paySalary", true);
	}
}
