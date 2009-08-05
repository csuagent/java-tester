package org.jtester.dbfit;

import org.jtester.dbfit.environment.DBEnvironment;
import org.jtester.dbfit.environment.MySqlEnvironment;
import org.jtester.dbfit.environment.OracleEnvironment;
import org.jtester.dbfit.environment.SqlServerEnvironment;
import org.jtester.unitils.database.DatabaseType;

import dbfit.environment.DerbyEnvironment;

public class DbFactory {
	private static DbFactory instance = null;

	public static DbFactory instance() {
		if (instance == null) {
			instance = new DbFactory();
		}
		return instance;
	}

	public DBEnvironment factory() {
		return mysql();
	}

	private DatabaseType databaseType = null;

	private DbFactory() {
		this.databaseType = DatabaseType.type();
	}

	public String getDataSource() {
		return databaseType.getConnUrl();
	}

	public String getDbUserName() {
		return databaseType.getUserName();
	}

	public String getDbPassword() {
		return databaseType.getUserPass();
	}

	public String getDriverName() {
		return databaseType.getDriveClass();
	}

	protected MySqlEnvironment mysql() {
		return new MySqlEnvironment();
	}

	protected DerbyEnvironment derby() {
		return new DerbyEnvironment();
	}

	protected OracleEnvironment oracle() {
		return new OracleEnvironment();
	}

	protected SqlServerEnvironment sqlServer() {
		return new SqlServerEnvironment();
	}
	/**
	 * "org.apache.derby.jdbc.EmbeddedDriver"
	 * "org.apache.derby.jdbc.ClientDriver" "com.ibm.db2.jcc.DB2Driver"
	 * "oracle.jdbc.OracleDriver" "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	 * "com.mysql.jdbc.Driver"
	 */
}
