package org.jtester.dbfit.fixture;

import java.sql.PreparedStatement;

import org.jtester.dbfit.environment.DBEnvironment;

import dbfit.environment.DbEnvironmentFactory;
import fit.Fixture;
import fit.Parse;

public class ExecuteFixture extends Fixture {
	private String statement;
	private DBEnvironment dbEnvironment;

	public ExecuteFixture() {
		dbEnvironment = DbEnvironmentFactory.getDefaultEnvironment();
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
