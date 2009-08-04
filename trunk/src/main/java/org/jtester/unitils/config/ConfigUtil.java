package org.jtester.unitils.config;

import java.util.Properties;

import org.jtester.unitils.database.DatabaseType;
import org.jtester.utility.StringUtil;
import org.unitils.core.Unitils;

public class ConfigUtil {
	public static final String dbexport_auto = "dbexport.auto";

	public static final String database_type = "database.type";

	public static final String PROPKEY_DATASOURCE_DRIVERCLASSNAME = "database.driverClassName";

	public static final String PROPKEY_DATASOURCE_URL = "database.url";

	public static final String PROPKEY_DATASOURCE_USERNAME = "database.userName";

	public static final String PROPKEY_DATASOURCE_PASSWORD = "database.password";

	public static final String DBMAINTAINER_DISABLECONSTRAINTS = "dbMaintainer.disableConstraints.enabled";

	public static final String REPLACED_SPRING_DATASOURCE = "database.replaced.spring.datasource";

	public static final Properties unitilscfg = Unitils.getInstance().getConfiguration();

	public static String property(String key) {
		return unitilscfg.getProperty(key);
	}

	public static String driverClazzName() {
		return unitilscfg.getProperty(PROPKEY_DATASOURCE_DRIVERCLASSNAME);
	}

	public static String databaseUrl() {
		return unitilscfg.getProperty(PROPKEY_DATASOURCE_URL);
	}

	public static String databaseUserName() {
		return unitilscfg.getProperty(PROPKEY_DATASOURCE_USERNAME);
	}

	public static String databasePassword() {
		return unitilscfg.getProperty(PROPKEY_DATASOURCE_PASSWORD);
	}

	public static boolean doesDisableConstraints() {
		String disableConstraints = unitilscfg.getProperty(DBMAINTAINER_DISABLECONSTRAINTS);
		return "TRUE".equalsIgnoreCase(disableConstraints);
	}

	public static boolean shouldReplacedSpringDataSource() {
		String replaced = unitilscfg.getProperty(REPLACED_SPRING_DATASOURCE);
		return "TRUE".equalsIgnoreCase(replaced);
	}

	public static String property(String value, String key) {
		if (StringUtil.isBlankOrNull(value)) {
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

	public static String databaseType() {
		String type = System.getProperty(database_type);// from vm
		if (!StringUtil.isBlankOrNull(type)) {
			return type;
		}
		type = unitilscfg.getProperty(database_type);// from property
		if (type == null) {
			type = "unsupport";
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
	}

	public static void setMemoryDbConfig(DatabaseType type) {
		unitilscfg.setProperty("database.driverClassName", type.getDriveClass());
		unitilscfg.setProperty("database.url", type.getConnUrl());
		unitilscfg.setProperty("database.userName", type.getUserName());
		unitilscfg.setProperty("database.password", type.getUserPass());
		unitilscfg.setProperty("database.schemaNames", type.getSchemas());
	}

	public static void setDatabaseDialect(DatabaseType type) {
		unitilscfg.setProperty("database.dialect", type.getDbUnitDialect());
	}
}
