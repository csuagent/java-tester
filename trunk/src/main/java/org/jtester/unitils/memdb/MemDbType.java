package org.jtester.unitils.memdb;

public enum MemDbType {
	/**
	 * H2Db
	 */
	H2DB("org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "",
			"org.hibernate.dialect.H2Dialect", "public"),
	/**
	 * HsqlDb
	 */
	HSQLDB("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:test", "sa", "",
			"org.hibernate.dialect.HSQLDialect", "public");

	private String clazz;
	private String url;
	private String user;
	private String pass;
	private String dialect;
	private String schemas;

	private MemDbType(String clazz, String url, String user, String pass,
			String dialect, String schemas) {
		this.clazz = clazz;
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.dialect = dialect;
		this.schemas = schemas;
	}

	public String getDriveClass() {
		return this.clazz;
	}

	public String getConnUrl() {
		return this.url;
	}

	public String getUserName() {
		return this.user;
	}

	public String getUserPass() {
		return this.pass;
	}

	public String getDialect() {
		return this.dialect;
	}

	public String getSchemas() {
		return this.schemas;
	}
}
