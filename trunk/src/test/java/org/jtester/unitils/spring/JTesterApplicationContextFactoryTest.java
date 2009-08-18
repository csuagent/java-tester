package org.jtester.unitils.spring;

import org.jtester.fortest.formock.SomeInterface;
import org.jtester.fortest.formock.SpringBeanService;
import org.jtester.fortest.formock.SomeInterface.SomeInterfaceImpl1;
import org.jtester.fortest.formock.SomeInterface.SomeInterfaceImpl2;
import org.jtester.unitils.jmock.MockBeanRegister;
import org.junit.Before;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class JTesterApplicationContextFactoryTest extends org.jtester.testng.JTester {
	private static final String TO_BE_OVERRIDEN_BEAN_NAME = "toBeOverriden";
	private static final String ANOTHER_BEAN_NAME = "springBeanService";

	private JTesterClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNoOverride() throws Throwable {
		MockBeanRegister.cleanMockBean();
		context = new JTesterClassPathXmlApplicationContext(
				new String[] { "org/jtester/fortest/spring/mock-spring-beans-test.xml" }, true, null);

		want.object(context.getBean(TO_BE_OVERRIDEN_BEAN_NAME)).clazIs(SomeInterfaceImpl1.class);

		SpringBeanService anotherBean = (SpringBeanService) context.getBean(ANOTHER_BEAN_NAME);
		want.object(anotherBean).notNull();
		want.object(anotherBean.getDependency1()).clazIs(SomeInterfaceImpl1.class);
		want.object(anotherBean.getDependency2()).clazIs(SomeInterfaceImpl1.class);
	}

	@Test
	public void testOverride() throws Throwable {
		MockBeanRegister.cleanMockBean();
		SomeInterface overrider = new SomeInterfaceImpl2();
		MockBeanRegister.addMockBean(TO_BE_OVERRIDEN_BEAN_NAME, overrider);
		context = new JTesterClassPathXmlApplicationContext(
				new String[] { "org/jtester/fortest/spring/mock-spring-beans-test.xml" }, true, null);

		SpringBeanService anotherBean = (SpringBeanService) context.getBean(ANOTHER_BEAN_NAME);
		want.object(anotherBean).notNull();
		want.object(anotherBean.getDependency1()).clazIs(SomeInterfaceImpl1.class);
		want.object(anotherBean.getDependency2()).clazIs(SomeInterfaceImpl1.class);

		want.object(context.getBean(TO_BE_OVERRIDEN_BEAN_NAME)).clazIs(SomeInterfaceImpl1.class);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void testOverride_failure() throws Throwable {
		MockBeanRegister.cleanMockBean();
		context = new JTesterClassPathXmlApplicationContext(
				new String[] { "org/jtester/fortest/spring/mock-spring-beans-test.xml" }, true, null);

		SpringBeanService anotherBean = (SpringBeanService) context.getBean(ANOTHER_BEAN_NAME);
		want.object(anotherBean).notNull();
		want.object(anotherBean.getDependency1()).clazIs(SomeInterfaceImpl2.class);
	}
}
