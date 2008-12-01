package org.jtester.unitils.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jtester.utility.ReflectUtil;
import org.unitils.core.Unitils;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;
import org.unitils.database.DatabaseModule;

public class DbSchemaExport {
	private static Log log = LogFactory.getLog(DbSchemaExport.class);

	private DataSourceType type;

	private SchemaExport export;

	private DbSupport dbSupport;

	public DbSchemaExport() {
		this(DataSourceType.H2DB);
	}

	public DbSchemaExport(DataSourceType type) {
		this.type = type;
		this.export = new SchemaExport(config());

		this.dbSupport = type.getDbSupport();
		SQLHandler sqlHandler = new DefaultSQLHandler(dataSource());
		this.dbSupport.init(config().getProperties(), sqlHandler, type.getSchema());
	}

	public void export() {
		this.disableDbMaintain();
		DatabaseModule module = Unitils.getInstance().getModulesRepository().getModuleOfType(DatabaseModule.class);
		try {
			ReflectUtil.setFieldValue(module, "updateDatabaseSchemaEnabled", false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		log.info("call hibernate tool:org.hibernate.tool.hbm2ddl.SchemaExport.create");
		this.export.execute(isScript(), true, false, true);
		log.info("remove foreign key constraints");
		this.dbSupport.disableReferentialConstraints();
		log.info("remove not null constraints");
		this.dbSupport.disableValueConstraints();
	}

	public Configuration config() {
		Configuration cfg = new AnnotationConfiguration().configure();
		cfg.setProperty("database.identifierQuoteString.h2db", "auto");
		cfg.setProperty("database.storedIndentifierCase.h2db", "auto");
		cfg.setProperty("database.identifierQuoteString.hsqldb", "auto");
		cfg.setProperty("database.storedIndentifierCase.hsqldb", "auto");
		cfg.setProperty("hibernate.show_sql", "false");

		cfg.setProperty("hibernate.connection.username", type.getUserName());
		cfg.setProperty("hibernate.connection.password", type.getUserPass());

		cfg.setProperty("hibernate.dialect", type.getDialect());
		cfg.setProperty("hibernate.connection.driver_class", type.getDriveClass());
		cfg.setProperty("hibernate.connection.url", type.getConnUrl());
		return cfg;
	}

	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(type.getDriveClass());
		dataSource.setUsername(type.getUserName());
		dataSource.setPassword(type.getUserPass());
		dataSource.setUrl(type.getConnUrl());

		return dataSource;
	}

	public void disableDbMaintain() {
		Properties cfg = Unitils.getInstance().getConfiguration();
		// cfg.setProperty("database.driverClassName", type.getDriveClass());
		// cfg.setProperty("database.url", type.getConnUrl());
		// cfg.setProperty("database.userName", type.getUserName());
		// cfg.setProperty("database.password", type.getUserPass());
		// cfg.setProperty("database.schemaNames", type.getSchema());
		// cfg.setProperty("database.dialect", "hsqldb");

		// disable dbmaintainer properties
		cfg.setProperty("updateDataBaseSchema.enabled", "false");
		cfg.setProperty("dbMaintainer.dbVersionSource.autoCreateVersionTable", "false");
		cfg.setProperty("dbMaintainer.disableConstraints.enabled", "false");
	}

	public static boolean isScript() {
		Properties unitilscfg = Unitils.getInstance().getConfiguration();
		String script = unitilscfg.getProperty("dbexport.script");
		if (script != null && script.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}
}
