package org.jtester.unitils.dbfit;

import java.lang.reflect.Method;
import java.util.Properties;

import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class DbFitModule implements Module {
	public void init(Properties configuration) {
		;
	}

	public void afterInit() {
		;
	}

	public TestListener getTestListener() {
		return new DbFitTestListener();
	}

	protected class DbFitTestListener extends TestListener {
		@Override
		public void beforeTestSetUp(Object testObject, Method testMethod) {

		}

		@Override
		public void afterTestMethod(Object testObject, Method testMethod, Throwable throwable) {

		}
	}
}
