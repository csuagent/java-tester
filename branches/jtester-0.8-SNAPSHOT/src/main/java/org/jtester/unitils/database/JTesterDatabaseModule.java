package org.jtester.unitils.database;

import org.jtester.unitils.jmock.MockBeans;
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
		public void beforeTestClass(Class<?> testClass) {
			if (!MockBeans.hasRegisteredMockBean() && !MockBeans.hasMockBean(testClass)) {
				return;
			}
			MockBeans.clean();
			SpringModule module = Unitils.getInstance().getModulesRepository().getModuleOfType(SpringModule.class);
			Class<?> clazz = testClass;
			while (clazz != null && !clazz.equals(org.jtester.testng.JTester.class)
					&& !clazz.equals(org.jtester.junit4.JTester.class)) {
				module.invalidateApplicationContext(clazz);
				clazz = clazz.getSuperclass();
			}
		}
	}
}
