package org.jtester.unitils.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.HsqldbDbSupport;
import org.unitils.core.dbsupport.MySqlDbSupport;

public enum DataSourceType {
	/**
	 * H2Db
	 */
	H2DB("org.h2.Driver", "org.hibernate.dialect.H2Dialect", "hsqldb", "public", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
			"sa", "", "public") {
		@Override
		public DbSupport getDbSupport() {
			return new H2DbSupport();
		}

		@Override
		public boolean autoExport() {
			return true;
		}
	},
	/**
	 * HsqlDb
	 */
	HSQLDB("org.hsqldb.jdbcDriver", "org.hibernate.dialect.HSQLDialect", "hsqldb", "public", "jdbc:hsqldb:mem:test",
			"sa", "", "public") {
		@Override
		public DbSupport getDbSupport() {
			return new HsqldbDbSupport();
		}

		@Override
		public boolean autoExport() {
			return true;
		}
	},
	/**
	 * mysql
	 */
	MYSQL("com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLInnoDBDialect", "mysql", "information_schema") {
		@Override
		public DbSupport getDbSupport() {
			return new MySqlDbSupport();
		}
	};
	private static Log log = LogFactory.getLog(DataSourceType.class);

	private String clazz = null;

	private String hibernate_dialect = null;

	private String url = null;

	private String user = null;

	private String pass = null;

	private String infoSchema = null;

	private String schema = null;

	private String dbunit_dialect = null;

	private DataSourceType(String clazz, String hibernate_dialect, String dbunit_dialect, String infoSchema,
			String url, String user, String pass, String schema) {
		this.clazz = clazz;
		this.hibernate_dialect = hibernate_dialect;
		this.dbunit_dialect = dbunit_dialect;
		this.infoSchema = infoSchema;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.schema = schema;
	}

	private DataSourceType(String clazz, String hibernate_dialect, String dbunit_dialect, String infoSchema) {
		this.clazz = clazz;
		this.hibernate_dialect = hibernate_dialect;
		this.dbunit_dialect = dbunit_dialect;
		this.infoSchema = infoSchema;
	}

	public String getDriveClass() {
		return this.clazz;
	}

	public String getHibernateDialect() {
		return this.hibernate_dialect;
	}

	public String getDbUnitDialect() {
		return this.dbunit_dialect;
	}

	public String getInfoSchema() {
		return this.infoSchema;
	}

	public String getConnUrl() {
		return ConfigUtil.property(this.url, "database.url");
	}

	public String getUserName() {
		return ConfigUtil.property(this.user, "database.userName");
	}

	public String getUserPass() {
		return ConfigUtil.property(this.pass, "database.password");
	}

	public String getSchema() {
		return ConfigUtil.property(this.schema, "database.schemaNames");
	}

	public static DataSourceType type() {
		try {
			String type = ConfigUtil.dataSourceType();
			return DataSourceType.valueOf(type.toUpperCase());
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	public boolean autoExport() {
		return ConfigUtil.autoExport();
	}

	public abstract DbSupport getDbSupport();
}
