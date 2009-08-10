package org.jtester.unitils.spring;

import org.jtester.fortest.formock.SomeInterface;
import org.jtester.fortest.formock.SpringBeanService;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.MockBean;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

@SpringApplicationContext( { "org/jtester/fortest/spring/mockbeans-withdependency.xml" })
@Test
public class MockBeanTest_MissingDependency extends JTester {
	@SpringBeanByName
	private SpringBeanService springBeanService1;

	@MockBean
	protected SomeInterface dependency2;

	public void testDependency_MockBean() {
		want.object(springBeanService1.getDependency1()).notNull();
		want.object(springBeanService1.getDependency2()).notNull();
	}

	@MockBean
	private SpringBeanService springBeanService2;

	@Test
	public void testDependency_UnMockBean() {
		checking(new Je() {
			{
				will.call.one(springBeanService2).getDependency1();
				will.returns.value(dependency2);
				will.call.one(springBeanService2).getDependency2();
				will.returns.value(dependency2);

			}
		});
		want.object(springBeanService2.getDependency1()).notNull();
		want.object(springBeanService2.getDependency2()).notNull();
	}
}
