package org.jtester.dbfit.fixture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.dbfit.DbFactory;
import org.jtester.dbfit.environment.DBEnvironment;
import org.jtester.dbfit.util.DataColumn;
import org.jtester.dbfit.util.DataTable;


public class QueryFixture extends RowSetFixture {
	private DBEnvironment dbEnvironment;
	private String query;
	private boolean isOrdered;
	private static Log log = LogFactory.getLog(QueryFixture.class);

	public QueryFixture() {
		dbEnvironment = DbFactory.instance().factory();// DbEnvironmentFactory.getDefaultEnvironment();
		isOrdered = false;
	}

	public QueryFixture(DBEnvironment environment, String query) {
		this(environment, query, false);
	}

	public QueryFixture(DBEnvironment environment, String query, boolean isOrdered) {
		this.dbEnvironment = environment;
		this.query = query;
		this.isOrdered = isOrdered;
	}

	public DataTable getDataTable() throws SQLException {
		if (query == null)
			query = args[0];
		if (query.startsWith("<<"))
			return getFromSymbol();
		log.info(String.format("Query: '%s'", query));
		PreparedStatement st = dbEnvironment.createStatementWithBoundFixtureSymbols(query);
		return new DataTable(st.executeQuery());
	}

	private DataTable getFromSymbol() throws SQLException {
		Object o = org.jtester.dbfit.util.SymbolUtil.getSymbol(query.substring(2).trim());
		if (o instanceof ResultSet) {
			return new DataTable((ResultSet) o);
		} else if (o instanceof DataTable) {
			return (DataTable) o;
		}
		throw new UnsupportedOperationException("Stored queries can only be used on symbols that contain result sets");
	}

	protected boolean isOrdered() {
		return isOrdered;
	}

	@Override
	protected Class<?> getJavaClassForColumn(DataColumn col) {
		return dbEnvironment.getJavaClass(col.getDbTypeName());
	}
}
