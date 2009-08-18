package org.jtester.dbfit.fixture;

import java.sql.PreparedStatement;

import org.jtester.dbfit.environment.DBEnvironment;

public class DbFitUtil {
	public static void cleanTable(DBEnvironment env, String table) {
		try {
			String statement = String.format("delete from %s ", table);
			PreparedStatement st = env.createStatementWithBoundFixtureSymbols(statement);
			st.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
