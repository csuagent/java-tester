package org.jtester.unitils.spring;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jtester.unitils.jmock.MockBeanRegister;
import org.unitils.core.ModulesRepository;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;
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

	private static ModulesRepository modulesRepository = Unitils.getInstance().getModulesRepository();

	public static void invalidSpringContext(Class<?> testedClass) {
		if (!modulesRepository.isModuleEnabled(SpringModule.class)) {
			return;
		}
		if (MockBeanRegister.hasRegisteredMockBean() || MockBeanRegister.hasMockBean(testedClass)) {
			MockBeanRegister.cleanRegister();
			MockBeanRegister.cleanMockBean();
			SpringModule module = modulesRepository.getModuleOfType(SpringModule.class);
			Class<?>[] claz = invalid(testedClass);
			module.invalidateApplicationContext(claz);
		}
	}

	private static Class<?>[] invalid(final Class<?> testedClass) {
		Class<?> clazz = testedClass;
		List<Class<?>> clazzes = new ArrayList<Class<?>>();

		while (clazz != null && !clazz.equals(org.jtester.testng.JTester.class)
				&& !clazz.equals(org.jtester.junit4.JTester.class)) {
			clazzes.add(clazz);
			clazz = clazz.getSuperclass();
		}
		return clazzes.toArray(new Class<?>[0]);
	}
}
