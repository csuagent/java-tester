package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(JTesterDataSource.class);

	private DataSourceType type;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		this.type = DataSourceType.type();
		this.createDb();

		this.setUsername(type.getUserName());
		this.setDriverClassName(type.getDriveClass());
		this.setUrl(type.getConnUrl());
		this.setPassword(type.getUserPass());
	}

	private BasicDataSource infoSchemaSource() {
		BasicDataSource source = new BasicDataSource();
		source.setUsername(type.getUserName());
		source.setDriverClassName(type.getDriveClass().replaceFirst(type.getSchema(), type.getInfoSchema()));
		source.setUrl(type.getConnUrl());
		source.setPassword(type.getUserPass());

		return source;
	}

	private void createDb() {
		SQLHandler sqlHandler = new DefaultSQLHandler(this.infoSchemaSource());
		String sql1 = "select * from information_schema.SCHEMATA where SCHEMA_NAME = '" + type.getSchema() + "' ";
		if (sqlHandler.getItemsAsStringSet(sql1).size() == 0) {
			String sql2 = "create database " + type.getSchema()
					+ " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;";
			sqlHandler.executeUpdate(sql2);
		}
	}
}
