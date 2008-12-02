package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	private static Log log = LogFactory.getLog(JTesterDataSource.class);

	private DataSourceType type;

	private DbSchemaExport export;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		this.type = DataSourceType.type();

		log.info("JTesterDataSource:begin create db?");
		DbCreator.createDb(type);
		log.info("JTesterDataSource:end create db.");
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
