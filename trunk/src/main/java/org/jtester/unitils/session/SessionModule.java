package org.jtester.unitils.session;

import java.util.Properties;

import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class SessionModule implements Module {

	public void afterInit() {
		// TODO Auto-generated method stub

	}

	public TestListener getTestListener() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(Properties configuration) {
		// TODO Auto-generated method stub

	}

	// public TestListener createTestListener() {
	// return new InSessionTestListener();
	// }
	//
	// public void init(Properties configuration) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// protected class InSessionTestListener extends TestListener {
	// protected PlatformTransactionManager transactionManager;
	//
	// protected TransactionStatus transactionStatus;
	//
	// private boolean isApplicationContextConfiguredForThisTest = false;
	//
	// private Session session;
	//
	// @Override
	// public void beforeTestMethod(Object testObject, Method testMethod) {
	// session = AnnotationUtils.getMethodOrClassLevelAnnotation(Session.class,
	// testMethod, testObject
	// .getClass());
	// isApplicationContextConfiguredForThisTest = false;
	// if (session != null && session.inSession()) {
	// SpringModule springModule =
	// Unitils.getInstance().getModulesRepository().getModuleOfType(
	// SpringModule.class);
	// isApplicationContextConfiguredForThisTest =
	// springModule.isApplicationContextConfiguredFor(testObject);
	// if (isApplicationContextConfiguredForThisTest) {
	// transactionManager = (PlatformTransactionManager)
	// springModule.getApplicationContext(testObject)
	// .getBean(session.transactionBeanName());
	// transactionStatus = transactionManager.getTransaction(new
	// DefaultTransactionDefinition());
	// }
	// }
	// }
	//
	// @Override
	// public void afterTestMethod(Object testObject, Method testMethod,
	// Throwable throwable) {
	// if (!isApplicationContextConfiguredForThisTest) {
	// return;
	// }
	// if (session.commit() == false || transactionStatus.isRollbackOnly()) {
	// transactionManager.rollback(this.transactionStatus);
	// } else {
	// transactionManager.commit(this.transactionStatus);
	// }
	// }
	// }
}
