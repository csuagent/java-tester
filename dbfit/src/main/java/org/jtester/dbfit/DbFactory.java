package org.jtester.dbfit;

import dbfit.environment.DBEnvironment;
import dbfit.environment.DerbyEnvironment;
import dbfit.environment.EmbeddedDerbyEnvironment;
import dbfit.environment.MySqlEnvironment;
import dbfit.environment.OracleEnvironment;
import dbfit.environment.SqlServerEnvironment;

public class DbFactory {

	public static DBEnvironment factory() {
		return mysql();
	}

	protected static MySqlEnvironment mysql() {
		return new MySqlEnvironment();
	}

	protected static DerbyEnvironment derby() {
		return new DerbyEnvironment();
	}

	protected static EmbeddedDerbyEnvironment embededDerby() {
		return new EmbeddedDerbyEnvironment();
	}

	protected static OracleEnvironment oracle() {
		return new OracleEnvironment();
	}

	protected static SqlServerEnvironment sqlServer() {
		return new SqlServerEnvironment();
	}

	public static String getDataSource() {
		return "";// TODO
	}

	public static String getDbUserName() {
		return "";// TODO
	}

	public static String getDbPassword() {
		return "";// TODO
	}

	public static String getDatabase() {
		return "";// TODO
	}
}
