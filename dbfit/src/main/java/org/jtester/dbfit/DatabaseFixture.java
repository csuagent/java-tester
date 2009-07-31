package org.jtester.dbfit;

import java.sql.SQLException;

import dbfit.environment.DBEnvironment;
import dbfit.util.Log;
import fit.Fixture;
import fitlibrary.SequenceFixture;
import fitlibrary.table.Table;
import fitlibrary.utility.TestResults;

public class DatabaseFixture extends SequenceFixture {
	protected DBEnvironment environment;

	public DatabaseFixture() {
		this.environment = DbFactory.factory();
	}

	@Override
	public void setUp(Table firstTable, TestResults testResults) {
		dbfit.util.Options.reset();
		super.setUp(firstTable, testResults);
	}

	@Override
	public void tearDown(Table firstTable, TestResults testResults) {
		try {
			Log.log("Rolling back");
			if (environment != null) {
				environment.rollback();
				environment.closeConnection();
			}
		} catch (Exception e) {
			Log.log(e);
		}
		super.tearDown(firstTable, testResults);
	}

	public void connect() throws SQLException {
		environment.connect(DbFactory.getDataSource(), DbFactory.getDbUserName(), DbFactory.getDbPassword(), DbFactory
				.getDatabase());
		environment.getConnection().setAutoCommit(false);
	}

	public void connect(String dataSource, String username, String password, String database) throws SQLException {
		environment.connect(dataSource, username, password, database);
		environment.getConnection().setAutoCommit(false);
	}

	public void connect(String dataSource, String username, String password) throws SQLException {
		environment.connect(dataSource, username, password);
		environment.getConnection().setAutoCommit(false);
	}

	public void connect(String connectionString) throws SQLException {
		environment.connect(connectionString);
		environment.getConnection().setAutoCommit(false);
	}

	// public void connectUsingFile(String filePath) throws SQLException,
	// IOException, FileNotFoundException {
	// environment.connectUsingFile(filePath);
	// environment.getConnection().setAutoCommit(false);
	// }

	public void close() throws SQLException {
		environment.rollback();
		environment.closeConnection();
	}

	public void setParameter(String name, String value) {
		dbfit.fixture.SetParameter.setParameter(name, value);
	}

	public void clearParameters() {
		dbfit.util.SymbolUtil.clearSymbols();
	}

	public Fixture query(String query) {
		return new dbfit.fixture.Query(environment, query);
	}

	public Fixture orderedQuery(String query) {
		return new dbfit.fixture.Query(environment, query, true);
	}

	public Fixture execute(String statement) {
		return new dbfit.fixture.Execute(environment, statement);
	}

	public Fixture executeProcedure(String statement) {
		return new dbfit.fixture.ExecuteProcedure(environment, statement);
	}

	public Fixture executeProcedureExpectException(String statement) {
		return new dbfit.fixture.ExecuteProcedure(environment, statement, true);
	}

	public Fixture executeProcedureExpectException(String statement, int code) {
		return new dbfit.fixture.ExecuteProcedure(environment, statement, code);
	}

	public Fixture insert(String tableName) {
		return new dbfit.fixture.Insert(environment, tableName);
	}

	public Fixture update(String tableName) {
		return new dbfit.fixture.Update(environment, tableName);
	}

	public Fixture clean() {
		return new dbfit.fixture.Clean(environment);
	}

	public Fixture testData(String type) {
		Log.log("Calling testData method with type '%s'", type);
		return new dbfit.fixture.TestData(environment, type);
	}

	public void rollback() throws SQLException {
		// System.out.println("Rolling back");
		environment.rollback();
		environment.getConnection().setAutoCommit(false);
	}

	public void commit() throws SQLException {
		// System.out.println("Committing");
		environment.commit();
		environment.getConnection().setAutoCommit(false);
	}

	public Fixture queryStats() {
		return new dbfit.fixture.QueryStats(environment);
	}

	public Fixture inspectProcedure(String procName) {
		return new dbfit.fixture.Inspect(environment, dbfit.fixture.Inspect.MODE_PROCEDURE, procName);
	}

	public Fixture inspectTable(String tableName) {
		return new dbfit.fixture.Inspect(environment, dbfit.fixture.Inspect.MODE_TABLE, tableName);
	}

	public Fixture inspectView(String tableName) {
		return new dbfit.fixture.Inspect(environment, dbfit.fixture.Inspect.MODE_TABLE, tableName);
	}

	public Fixture inspectQuery(String query) {
		return new dbfit.fixture.Inspect(environment, dbfit.fixture.Inspect.MODE_QUERY, query);
	}

	public Fixture storeQuery(String query, String symbolName) {
		return new dbfit.fixture.StoreQuery(environment, query, symbolName);
	}

	public Fixture compareStoredQueries(String symbol1, String symbol2) {
		return new dbfit.fixture.CompareStoredQueries(environment, symbol1, symbol2);
	}

	public void setOption(String option, String value) {
		dbfit.util.Options.setOption(option, value);
	}
}
