package org.jtester.unitils.memdb;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.jtester.unitils.database.H2DbSupport;
import org.jtester.unitils.database.DataSourceType;
import org.unitils.core.Unitils;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.HsqldbDbSupport;

public final class MemDbConfigUtil {
	private static Log log = LogFactory.getLog(MemDbConfigUtil.class);

	private static Configuration cfg = null;

	private static BasicDataSource dataSource = null;

	private static DataSourceType dbtype = null;

	private static Boolean isMemDb = null;

	private static Boolean script = null;

	public static Configuration getConfig() {
		if (cfg != null) {
			return cfg;
		}
		cfg = new AnnotationConfiguration().configure();
		cfg.setProperty("database.identifierQuoteString.h2db", "auto");
		cfg.setProperty("database.storedIndentifierCase.h2db", "auto");
		cfg.setProperty("database.identifierQuoteString.hsqldb", "auto");
		cfg.setProperty("database.storedIndentifierCase.hsqldb", "auto");
		cfg.setProperty("hibernate.show_sql", "false");

		cfg.setProperty("hibernate.connection.username", dbtype.getUserName());
		cfg.setProperty("hibernate.connection.password", dbtype.getUserPass());

		cfg.setProperty("hibernate.dialect", dbtype.getDialect());
		cfg.setProperty("hibernate.connection.driver_class", dbtype.getDriveClass());
		cfg.setProperty("hibernate.connection.url", dbtype.getConnUrl());
		return cfg;
	}

	public static boolean isScript() {
		if (script != null) {
			return script;
		}
		Properties unitilscfg = Unitils.getInstance().getConfiguration();
		String show = unitilscfg.getProperty("memdb.showsql");
		if (show.equalsIgnoreCase("true")) {
			script = true;
		} else {
			script = false;
		}
		return script;
	}

	public static DataSourceType getMemDbType() {
		if (isMemDbType()) {
			return dbtype;
		} else {
			return DataSourceType.H2DB;
		}
	}

	public static boolean isMemDbType() {
		if (isMemDb != null) {
			return isMemDb;
		}
		// form vm
		String type = System.getProperty("memdb.type");
		// from property
		if (type == null) {
			Properties unitilscfg = Unitils.getInstance().getConfiguration();
			type = unitilscfg.getProperty("memdb.type");
		}
		if (type == null) {
			isMemDb = false;
			return isMemDb;
		}
		try {
			dbtype = DataSourceType.valueOf(type.toUpperCase());
			isMemDb = true;
		} catch (IllegalArgumentException e) {
			log.warn(e.getMessage());
			dbtype = null;
			isMemDb = false;
		}
		return isMemDb;
	}

	public static DbSupport getDbSupport(DataSourceType type) {
		if (type == DataSourceType.HSQLDB) {
			return new HsqldbDbSupport();
		} else {
			return new H2DbSupport();
		}
	}

	public static DataSource getDataSource() {
		if (dataSource != null) {
			return dataSource;
		}
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbtype.getDriveClass());
		dataSource.setUsername(dbtype.getUserName());
		dataSource.setPassword(dbtype.getUserPass());
		dataSource.setUrl(dbtype.getConnUrl());

		return dataSource;
	}

	public static Properties getProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.connection.driver_class", dbtype.getDriveClass());
		props.setProperty("hibernate.connection.url", dbtype.getConnUrl());
		props.setProperty("hibernate.connection.username", dbtype.getUserName());
		props.setProperty("hibernate.connection.password", dbtype.getUserPass());
		return props;
	}

	public static void uptUnitilsConfigs() {
		Properties cfg = Unitils.getInstance().getConfiguration();
		cfg.setProperty("database.driverClassName", dbtype.getDriveClass());
		cfg.setProperty("database.url", dbtype.getConnUrl());
		cfg.setProperty("database.userName", dbtype.getUserName());
		cfg.setProperty("database.password", dbtype.getUserPass());
		cfg.setProperty("database.schemaNames", dbtype.getSchema());
		cfg.setProperty("database.dialect", "hsqldb");

		// disable dbmaintainer properties
		cfg.setProperty("updateDataBaseSchema.enabled", "false");
		cfg.setProperty("dbMaintainer.dbVersionSource.autoCreateVersionTable", "false");
		cfg.setProperty("dbMaintainer.disableConstraints.enabled", "false");
	}

	private MemDbConfigUtil() {

	}
}
