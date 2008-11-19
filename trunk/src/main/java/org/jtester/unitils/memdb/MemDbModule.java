package org.jtester.unitils.memdb;

import java.util.Properties;

import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class MemDbModule implements Module {

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
	// return new TestDbModuleListener();
	// }
	//
	// public void init(Properties configuration) {
	// }
	//
	// protected class TestDbModuleListener extends TestListener {
	//
	// public void beforeAll() {
	// if (MemDbConfigUtil.isMemDbType()) {
	// MemDbConfigUtil.uptUnitilsConfigs();
	// disaledDatabaseModule();
	// DbSchemaExport export = new
	// DbSchemaExport(MemDbConfigUtil.getMemDbType());
	// export.export();
	// }
	// }
	//
	// protected TestDbModuleListener() {
	// super();
	// }
	// }
	//
	// private void disaledDatabaseModule() {
	// DatabaseModule module =
	// Unitils.getInstance().getModulesRepository().getModuleOfType(
	// DatabaseModule.class);
	//
	// Field field = null;
	// try {
	// field =
	// DatabaseModule.class.getDeclaredField("updateDatabaseSchemaEnabled");
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (NoSuchFieldException e) {
	// e.printStackTrace();
	// }
	// boolean accessible = field.isAccessible();
	// try {
	// field.setAccessible(true);
	// field.set(module, false);
	// } catch (Exception e) {
	// throw new RuntimeException("Unable to update the value in field[" +
	// field.getName() + "]", e);
	// } finally {
	// field.setAccessible(accessible);
	// }
	// }
}
