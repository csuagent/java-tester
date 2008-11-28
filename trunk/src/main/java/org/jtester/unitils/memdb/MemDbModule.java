package org.jtester.unitils.memdb;

import java.util.Properties;

import org.jtester.utility.ReflectUtil;
import org.unitils.core.Module;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;
import org.unitils.database.DatabaseModule;

public class MemDbModule implements Module {

	public void init(Properties configuration) {
		if (MemDbConfigUtil.isMemDbType()) {
			MemDbConfigUtil.uptUnitilsConfigs();
			// disable DatabaseSchemaEnabled
			DatabaseModule module = Unitils.getInstance().getModulesRepository().getModuleOfType(DatabaseModule.class);
			ReflectUtil.setFieldValue(module, "updateDatabaseSchemaEnabled", false);
			// export hibernate db schema
			DbSchemaExport export = new DbSchemaExport(MemDbConfigUtil.getMemDbType());
			export.export();
		}
	}

	public void afterInit() {
	}

	public TestListener getTestListener() {
		return new MemDbModuleListener();
	}

	protected class MemDbModuleListener extends TestListener {
	}
}
