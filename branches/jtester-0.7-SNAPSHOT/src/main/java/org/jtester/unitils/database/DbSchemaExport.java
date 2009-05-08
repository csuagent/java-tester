package org.jtester.unitils.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jtester.exception.JTesterException;
import org.jtester.unitils.config.ConfigUtil;
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
		this.dbSupport.init(ConfigUtil.config(), sqlHandler, type.getSchema());
	}

	public void export() {
		ConfigUtil.disableDbMaintain();
		DatabaseModule module = Unitils.getInstance().getModulesRepository().getModuleOfType(DatabaseModule.class);
		try {
			ReflectUtil.setFieldValue(module, "updateDatabaseSchemaEnabled", false);
		} catch (Exception e) {
			throw new JTesterException(e);
		}

		log.info("call hibernate tool:org.hibernate.tool.hbm2ddl.SchemaExport.create");
		this.export.execute(ConfigUtil.isScript(), true, false, true);
		log.info("remove foreign key constraints");
		this.dbSupport.disableReferentialConstraints();
		log.info("remove not null constraints");
		this.dbSupport.disableValueConstraints();
	}

	public Configuration config() {
		Configuration cfg = new AnnotationConfiguration().configure();
		cfg.setProperty("hibernate.show_sql", "false");

		cfg.setProperty("hibernate.connection.username", type.getUserName());
		cfg.setProperty("hibernate.connection.password", type.getUserPass());

		cfg.setProperty("hibernate.dialect", type.getHibernateDialect());
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
}
