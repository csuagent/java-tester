package org.jtester.unitils.spring;

import java.lang.reflect.Method;

import org.unitils.core.TestListener;
import org.unitils.spring.SpringModule;

public class JTesterSpringModule extends SpringModule {
	@Override
	public TestListener getTestListener() {
		return new JtesterSpringTestListener();
	}

	/**
	 * The {@link TestListener} for this module
	 */
	protected class JtesterSpringTestListener extends SpringTestListener {

		@Override
		public void beforeTestSetUp(Object testObject, Method testMethod) {
			injectApplicationContext(testObject);
			injectSpringBeans(testObject);
			injectSpringBeansByType(testObject);
			injectSpringBeansByName(testObject);
		}
	}
}
