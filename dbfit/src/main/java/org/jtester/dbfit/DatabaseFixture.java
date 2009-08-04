package org.jtester.dbfit;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.dbfit.fixture.CleanFixture;
import org.jtester.dbfit.fixture.ExecuteFixture;
import org.jtester.dbfit.fixture.ExecuteProcedureFixture;
import org.jtester.dbfit.fixture.InsertFixture;
import org.jtester.dbfit.fixture.QueryFixture;
import org.jtester.dbfit.fixture.StoreQueryFixture;
import org.jtester.dbfit.fixture.TestDataFixture;
import org.jtester.dbfit.fixture.UpdateFixture;

import dbfit.environment.DBEnvironment;
import fit.Fixture;
import fitlibrary.SequenceFixture;
import fitlibrary.table.Table;
import fitlibrary.utility.TestResults;

public class DatabaseFixture extends SequenceFixture {
	protected DBEnvironment environment;

	private static Log log = LogFactory.getLog(DatabaseFixture.class);

	public DatabaseFixture() {
		this.environment = DbFactory.instance().factory();
	}

	@Override
	public void setUp(Table firstTable, TestResults testResults) {
		dbfit.util.Options.reset();
		super.setUp(firstTable, testResults);
	}

	@Override
	public void tearDown(Table firstTable, TestResults testResults) {
		try {
			log.info("Rolling back");
			if (environment != null) {
				environment.rollback();
				environment.closeConnection();
			}
		} catch (Exception e) {
			log.error(e);
		}
		super.tearDown(firstTable, testResults);
	}

	public void connect() throws SQLException {
		DbFactory db = DbFactory.instance();
		environment.connect(db.getDataSource(), db.getDbUserName(), db.getDbPassword());
		environment.getConnection().setAutoCommit(false);
	}

	public void connect(String dataSource, String username, String password, String database) throws SQLException {
		environment.connect(dataSource, username, password);
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
		return new QueryFixture(environment, query);
	}

	public Fixture orderedQuery(String query) {
		return new QueryFixture(environment, query, true);
	}

	public Fixture execute(String statement) {
		return new ExecuteFixture(environment, statement);
	}

	public Fixture executeProcedure(String statement) {
		return new ExecuteProcedureFixture(environment, statement);
	}

	public Fixture executeProcedureExpectException(String statement) {
		return new ExecuteProcedureFixture(environment, statement, true);
	}

	public Fixture executeProcedureExpectException(String statement, int code) {
		return new ExecuteProcedureFixture(environment, statement, code);
	}

	public Fixture insert(String tableName) {
		return new InsertFixture(environment, tableName);
	}

	public Fixture update(String tableName) {
		return new UpdateFixture(environment, tableName);
	}

	public Fixture clean() {
		return new CleanFixture(environment);
	}

	public Fixture testData(String type) {
		log.info(String.format("Calling testData method with type '%s'", type));
		return new TestDataFixture(environment, type);
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
		return new StoreQueryFixture(environment, query, symbolName);
	}

	public Fixture compareStoredQueries(String symbol1, String symbol2) {
		return new dbfit.fixture.CompareStoredQueries(environment, symbol1, symbol2);
	}

	public void setOption(String option, String value) {
		dbfit.util.Options.setOption(option, value);
	}
}
