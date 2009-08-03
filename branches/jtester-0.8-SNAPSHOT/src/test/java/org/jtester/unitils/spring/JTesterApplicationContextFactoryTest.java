package org.jtester.unitils.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.jtester.unitils.spring.SpringBeanService.SomeInterface;
import org.jtester.unitils.spring.SpringBeanService.SomeInterfaceImpl;
import org.jtester.unitils.spring.SpringBeanService.SomeInterfaceImpl2;
import org.junit.Before;
import org.testng.annotations.Test;

public class JTesterApplicationContextFactoryTest extends org.jtester.testng.JTester {
	private static final String TO_BE_OVERRIDEN_BEAN_NAME = "toBeOverriden";
	private static final String ANOTHER_BEAN_NAME = "springBeanService";

	private UseMockClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNoOverride() throws Throwable {
		context = new UseMockClassPathXmlApplicationContext(
				new String[] { "org/jtester/unitils/spring/mock-spring-beans-test.xml" }, true, null, null);
		assertEquals(SomeInterfaceImpl.class, context.getBean(TO_BE_OVERRIDEN_BEAN_NAME).getClass());
		SpringBeanService anotherBean = (SpringBeanService) context.getBean(ANOTHER_BEAN_NAME);
		assertNotNull(anotherBean);
		assertEquals(SomeInterfaceImpl.class, anotherBean.getDependency().getClass());
		assertEquals(SomeInterfaceImpl.class, anotherBean.getDependency2().getClass());
	}

	@Test
	public void testOverride() throws Throwable {
		SomeInterface overrider = new SomeInterfaceImpl2();
		Map<String, Object> singletonsByBeanName = new HashMap<String, Object>();
		singletonsByBeanName.put(TO_BE_OVERRIDEN_BEAN_NAME, overrider);
		System.out.println("begin initial spring context");
		context = new UseMockClassPathXmlApplicationContext(
				new String[] { "org/jtester/unitils/spring/mock-spring-beans-test.xml" }, true, null,
				singletonsByBeanName);

		System.out.println("after initial spring context");

		SpringBeanService anotherBean = (SpringBeanService) context.getBean(ANOTHER_BEAN_NAME);
		assertNotNull(anotherBean);
		assertEquals(SomeInterfaceImpl2.class, anotherBean.getDependency().getClass());
		assertEquals(SomeInterfaceImpl2.class, anotherBean.getDependency2().getClass());

		assertEquals(SomeInterfaceImpl2.class, context.getBean(TO_BE_OVERRIDEN_BEAN_NAME).getClass());
	}
}
