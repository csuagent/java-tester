package org.jtester.unitils.database;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.unitils.core.Unitils;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.HsqldbDbSupport;
import org.unitils.core.dbsupport.MySqlDbSupport;

public enum DataSourceType {
	/**
	 * H2Db
	 */
	H2DB("org.h2.Driver", "org.hibernate.dialect.H2Dialect", "public", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "",
			"public") {
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
	HSQLDB("org.hsqldb.jdbcDriver", "org.hibernate.dialect.HSQLDialect", "public", "jdbc:hsqldb:mem:test", "sa", "",
			"public") {
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
	MYSQL("com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLInnoDBDialect", "information_schema") {
		@Override
		public DbSupport getDbSupport() {
			return new MySqlDbSupport();
		}
	};
	private static Log log = LogFactory.getLog(DataSourceType.class);

	private static Properties cfg = Unitils.getInstance().getConfiguration();

	private String clazz = null;

	private String dialect = null;

	private String url = null;

	private String user = null;

	private String pass = null;

	private String infoSchema = null;

	private String schema = null;

	private DataSourceType(String clazz, String dialect, String infoSchema, String url, String user, String pass,
			String schema) {
		this.clazz = clazz;
		this.dialect = dialect;
		this.infoSchema = infoSchema;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.schema = schema;
	}

	private DataSourceType(String clazz, String dialect, String infoSchema) {
		this.clazz = clazz;
		this.dialect = dialect;
		this.infoSchema = infoSchema;
	}

	public String getDriveClass() {
		return this.clazz;
	}

	public String getDialect() {
		return this.dialect;
	}

	public String getInfoSchema() {
		return this.infoSchema;
	}

	public String getConnUrl() {
		if (url != null) {
			return url;
		} else {
			return cfg.getProperty("database.url");
		}
	}

	public String getUserName() {
		if (user != null) {
			return this.user;
		} else {
			return cfg.getProperty("database.userName");
		}
	}

	public String getUserPass() {
		if (pass != null) {
			return this.pass;
		} else {
			return cfg.getProperty("database.password");
		}
	}

	public String getSchema() {
		if (this.schema != null) {
			return this.schema;
		} else {
			return cfg.getProperty("database.schemaName");
		}
	}

	public static DataSourceType type() {
		// form vm
		String type = System.getProperty("datasource.type");
		// from property
		if (type == null) {
			type = cfg.getProperty("datasource.type");
		}
		DataSourceType dataSourceType = DataSourceType.H2DB;

		try {
			if (type != null) {
				dataSourceType = DataSourceType.valueOf(type.toUpperCase());
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			dataSourceType = DataSourceType.H2DB;
		}
		return dataSourceType;
	}

	public boolean autoExport() {
		Properties unitilscfg = Unitils.getInstance().getConfiguration();
		String auto = unitilscfg.getProperty("dbexport.auto");
		if (auto != null && auto.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	public abstract DbSupport getDbSupport();
}
