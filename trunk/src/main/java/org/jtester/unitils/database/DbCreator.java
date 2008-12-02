package org.jtester.unitils.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;

public class DbCreator {
	public static void createDb(DataSourceType type) {
		if (type == DataSourceType.MYSQL) {
			createMySqlDb(type);
		} else {
			// TODO
		}
	}

	private static void createMySqlDb(DataSourceType type) {
		String url = type.getConnUrl().replaceFirst(type.getSchema(), "information_schema");
		DataSource datasource = datasource(type, url);
		SQLHandler sqlHandler = new DefaultSQLHandler(datasource);
		String sql1 = "select * from information_schema.SCHEMATA where SCHEMA_NAME = '" + type.getSchema() + "' ";
		if (sqlHandler.getItemsAsStringSet(sql1).size() == 0) {
			String sql2 = "create database " + type.getSchema()
					+ " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;";
			sqlHandler.executeUpdate(sql2);
		}
	}

	private static DataSource datasource(DataSourceType type, String url) {
		BasicDataSource source = new BasicDataSource();
		source.setUsername(type.getUserName());
		source.setDriverClassName(type.getDriveClass());
		source.setUrl(url);
		source.setPassword(type.getUserPass());
		return source;
	}
}
