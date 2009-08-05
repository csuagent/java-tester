package org.jtester.dbfit;

import org.jtester.dbfit.environment.DBEnvironment;
import org.jtester.dbfit.environment.MySqlEnvironment;
import org.jtester.dbfit.environment.OracleEnvironment;
import org.jtester.dbfit.environment.SqlServerEnvironment;
import org.jtester.unitils.database.DatabaseType;

public class DbFactory {
	private static DbFactory instance = null;

	public static DbFactory instance() {
		if (instance == null) {
			instance = new DbFactory();
		}
		return instance;
	}

	public DBEnvironment factory() {
		switch (databaseType) {
		case MYSQL:
			return new MySqlEnvironment();
		case ORACLE:
			return new OracleEnvironment();
		case SqlServer:
			return new SqlServerEnvironment();
		default:
			throw new RuntimeException("unsupport database type," + databaseType.getDriveClass());
		}
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

	/**
	 * "org.apache.derby.jdbc.EmbeddedDriver" <br>
	 * "org.apache.derby.jdbc.ClientDriver" <br>
	 * "com.ibm.db2.jcc.DB2Driver"<br>
	 * "oracle.jdbc.OracleDriver" <br>
	 * "com.microsoft.sqlserver.jdbc.SQLServerDriver"<br>
	 * "com.mysql.jdbc.Driver"
	 */
}
