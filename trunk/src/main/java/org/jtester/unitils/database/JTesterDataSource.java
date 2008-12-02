package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	@SuppressWarnings("unused")
	private static Log log = LogFactory.getLog(JTesterDataSource.class);

	private DataSourceType type;

	private DbSchemaExport export;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		this.type = DataSourceType.type();

		DbCreator.createDb(type);
		if (type.autoExport()) {
			this.export = new DbSchemaExport(type);
			export.export();
		}
		this.setUsername(type.getUserName());
		this.setDriverClassName(type.getDriveClass());
		this.setUrl(type.getConnUrl());
		this.setPassword(type.getUserPass());
	}
}
