package org.jtester.unitils.spring;

import org.jtester.fortest.service.SpringBeanService;
import org.jtester.fortest.service.SpringBeanService.SomeInterface;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;

@Test(groups = "jtester")
public class MockTest_ByName extends JTester {

	private SpringBeanService springBeanService = new SpringBeanService();

	@Mock(injectInto = "springBeanService", byProperty = "dependency")
	private SomeInterface someInterface1;

	@Mock(injectInto = "springBeanService", byProperty = "dependency2")
	private SomeInterface someInterface2;

	@Test
	public void testMock_ByName() {
		want.object(springBeanService.getDependency()).same(someInterface1);
		want.object(springBeanService.getDependency2()).same(someInterface2);
	}
}
