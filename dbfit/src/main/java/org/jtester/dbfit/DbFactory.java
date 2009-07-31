package org.jtester.dbfit;

import org.jtester.unitils.config.ConfigUtil;
import org.jtester.unitils.database.DataSourceType;

import dbfit.environment.DBEnvironment;
import dbfit.environment.DerbyEnvironment;
import dbfit.environment.MySqlEnvironment;
import dbfit.environment.OracleEnvironment;
import dbfit.environment.SqlServerEnvironment;

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

	private DataSourceType databaseType = null;

	private DbFactory() {
		this.databaseType = DataSourceType.type();
	}

	public String getDataSource() {
		return databaseType == null ? ConfigUtil.databaseUrl() : databaseType
				.getConnUrl();
	}

	public String getDbUserName() {
		return databaseType == null ? ConfigUtil.databaseUserName()
				: databaseType.getUserName();
	}

	public String getDbPassword() {
		return databaseType == null ? ConfigUtil.databasePassword()
				: databaseType.getUserPass();
	}

	public String getDriverName() {
		return databaseType == null ? ConfigUtil.driverClazzName()
				: databaseType.getDriveClass();
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
