package org.jtester.unitils.database;

import org.jtester.unitils.jmock.MockBeanRegister;
import org.unitils.core.ModulesRepository;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;
import org.unitils.database.DatabaseModule;
import org.unitils.spring.SpringModule;

public class JTesterDatabaseModule extends DatabaseModule {
	@Override
	public TestListener getTestListener() {
		return new JtesterDatabaseTestListener();
	}

	/**
	 * The {@link TestListener} for this module
	 */
	protected class JtesterDatabaseTestListener extends DatabaseTestListener {
		/**
		 * o 如果MockBean已经注册，那么Spring Bean已经被污染，需要重新load context<br>
		 * o 如果这个测试类中有MockBean对象，那么spring bean就必须被mock，也要重新load context <br>
		 * o 重启spring context放在这里的原因是，spring初始化最早发生在database的事务期间
		 */
		public void beforeTestClass(Class<?> testClass) {
			ModulesRepository modulesRepository = Unitils.getInstance().getModulesRepository();
			if (!modulesRepository.isModuleEnabled(SpringModule.class)) {
				return;
			}
			if (!MockBeanRegister.hasRegisteredMockBean() && !MockBeanRegister.hasMockBean(testClass)) {
				return;
			}
			MockBeanRegister.clean();
			SpringModule module = modulesRepository.getModuleOfType(SpringModule.class);
			Class<?> clazz = testClass;
			while (clazz != null && !clazz.equals(org.jtester.testng.JTester.class)
					&& !clazz.equals(org.jtester.junit4.JTester.class)) {
				module.invalidateApplicationContext(clazz);
				clazz = clazz.getSuperclass();
			}
		}
	}
}
