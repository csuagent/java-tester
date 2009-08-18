package org.jtester.dbfit.fixture;

import java.sql.PreparedStatement;

import org.jtester.dbfit.DbFactory;
import org.jtester.dbfit.environment.DBEnvironment;

import fit.Fixture;
import fit.Parse;

public class ExecuteFixture extends Fixture {
	private String statement;
	private DBEnvironment dbEnvironment;

	public ExecuteFixture() {
		dbEnvironment = DbFactory.instance().factory();
	}

	public ExecuteFixture(DBEnvironment env, String statement) {
		this.statement = statement;
		this.dbEnvironment = env;
	}

	public void doRows(Parse rows) {
		try {
			if (statement == null)
				statement = args[0];
			PreparedStatement st = dbEnvironment.createStatementWithBoundFixtureSymbols(statement);
			st.execute();
		} catch (Exception e) {
			throw new Error(e);
		}
	}
}
