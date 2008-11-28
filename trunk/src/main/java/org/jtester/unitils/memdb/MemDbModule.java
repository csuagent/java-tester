package org.jtester.unitils.memdb;

import java.lang.reflect.Field;
import java.util.Properties;

import org.unitils.core.Module;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;
import org.unitils.database.DatabaseModule;

public class MemDbModule implements Module {

	public void init(Properties configuration) {
		if (MemDbConfigUtil.isMemDbType()) {
			MemDbConfigUtil.uptUnitilsConfigs();
			disaledDatabaseModule();
			DbSchemaExport export = new DbSchemaExport(MemDbConfigUtil.getMemDbType());
			export.export();
		}
	}

	public void afterInit() {
	}

	public TestListener getTestListener() {
		return new MemDbModuleListener();
	}

	private void disaledDatabaseModule() {
		DatabaseModule module = Unitils.getInstance().getModulesRepository().getModuleOfType(DatabaseModule.class);

		Field field = null;
		try {
			field = DatabaseModule.class.getDeclaredField("updateDatabaseSchemaEnabled");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			field.set(module, false);
		} catch (Exception e) {
			throw new RuntimeException("Unable to update the value in field[" + field.getName() + "]", e);
		} finally {
			field.setAccessible(accessible);
		}
	}

	protected class MemDbModuleListener extends TestListener {
	}
}
