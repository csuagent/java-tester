package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		DataSourceType type = DataSourceType.type();
		this.initDb();

		this.setUsername(type.getUserName());
		this.setDriverClassName(type.getDriveClass());
		this.setUrl(type.getConnUrl());
		this.setPassword(type.getUserPass());
	}

	private void initDb() {

	}
}
