package org.jtester.unitils.dbfit;

import static org.unitils.util.AnnotationUtils.getMethodOrClassLevelAnnotation;

import java.lang.reflect.Method;
import java.util.Properties;

import org.jtester.dbfit.JTesterRunner;
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
			DbFit dbfit = getMethodOrClassLevelAnnotation(DbFit.class, testMethod, testObject.getClass());
			if (dbfit == null) {
				return;
			}
			String[] wikis = dbfit.when();
			runDbWiki(wikis);
		}

		@Override
		public void afterTestTearDown(Object testObject, Method testMethod) {
			DbFit dbfit = getMethodOrClassLevelAnnotation(DbFit.class, testMethod, testObject.getClass());
			if (dbfit == null) {
				return;
			}
			String[] wikis = dbfit.then();
			runDbWiki(wikis);
		}
	}

	public void runDbWiki(String[] wikis) {
		for (String wiki : wikis) {
			JTesterRunner.run(wiki);
		}
	}
}
