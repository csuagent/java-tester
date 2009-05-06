package org.jtester.unitils.config;

import java.util.Properties;

import org.jtester.unitils.database.DataSourceType;
import org.unitils.core.Unitils;

public class ConfigUtil {
	public static final String dbexport_auto = "dbexport.auto";

	public static final String datasource_type = "datasource.type";

	public static final Properties unitilscfg = Unitils.getInstance().getConfiguration();

	public static String property(String key) {
		return unitilscfg.getProperty(key);
	}

	public static String property(String value, String key) {
		if (value == null || "".equalsIgnoreCase(value.trim())) {
			return unitilscfg.getProperty(key);
		} else {
			return value;
		}
	}

	public static boolean autoExport() {
		String auto = unitilscfg.getProperty(dbexport_auto);
		if (auto != null && auto.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isScript() {
		String script = unitilscfg.getProperty("dbexport.script");
		if (script != null && script.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	public static String dataSource() {
		// form vm
		String type = System.getProperty(datasource_type);
		// from property
		if (type == null) {
			type = unitilscfg.getProperty(datasource_type);
		}
		if (type == null) {
			type = "H2DB";
		}
		return type;
	}

	public static Properties config() {
		return unitilscfg;
	}

	public static void disableDbMaintain() {
		// disable dbmaintainer properties
		unitilscfg.setProperty("updateDataBaseSchema.enabled", "false");
		unitilscfg.setProperty("dbMaintainer.dbVersionSource.autoCreateVersionTable", "false");
		unitilscfg.setProperty("dbMaintainer.disableConstraints.enabled", "false");
	}

	public static void setDbUnitConfig(DataSourceType type) {
		unitilscfg.setProperty("database.driverClassName", type.getDriveClass());
		unitilscfg.setProperty("database.url", type.getConnUrl());
		unitilscfg.setProperty("database.userName", type.getUserName());
		unitilscfg.setProperty("database.password", type.getUserPass());
		unitilscfg.setProperty("database.schemaNames", type.getSchema());
		unitilscfg.setProperty("database.dialect", type.getDbUnitDialect());
	}
}
