package org.jtester.unitils.database;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.unitils.core.Unitils;

public enum DataSourceType {
	/**
	 * H2Db
	 */
	H2DB("org.h2.Driver", "org.hibernate.dialect.H2Dialect", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "", "public"),
	/**
	 * HsqlDb
	 */
	HSQLDB("org.hsqldb.jdbcDriver", "org.hibernate.dialect.HSQLDialect", "jdbc:hsqldb:mem:test", "sa", "", "public"),
	/**
	 * mysql
	 */
	MYSQL("com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLInnoDBDialect");
	private static Log log = LogFactory.getLog(DataSourceType.class);

	private static Properties cfg = Unitils.getInstance().getConfiguration();

	private String clazz = null;

	private String dialect = null;

	private String url = null;

	private String user = null;

	private String pass = null;

	private String schemas = null;

	private DataSourceType(String clazz, String dialect, String url, String user, String pass, String schemas) {
		this.clazz = clazz;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.dialect = dialect;
		this.schemas = schemas;
	}

	private DataSourceType(String clazz, String dialect) {
		this.clazz = clazz;
		this.dialect = dialect;
	}

	public String getDriveClass() {
		return this.clazz;
	}

	public String getDialect() {
		return this.dialect;
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

	public String getSchemas() {
		if (schemas != null) {
			return this.schemas;
		} else {
			return cfg.getProperty("database.schemaNames");
		}
	}

	public static DataSourceType type() {
		// form vm
		String type = System.getProperty("memdb.type");
		// from property
		if (type == null) {
			type = cfg.getProperty("memdb.type");
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
}
