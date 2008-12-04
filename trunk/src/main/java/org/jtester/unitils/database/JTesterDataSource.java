package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	private static Log log = LogFactory.getLog(JTesterDataSource.class);

	private static boolean db_has_been_created = false;

	private DataSourceType type;

	private DbSchemaExport export;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		this.type = DataSourceType.type();
		ConfigUtil.setDbUnitDialect(type);
		this.createDataBase();

		this.setUsername(type.getUserName());
		this.setDriverClassName(type.getDriveClass());
		this.setUrl(type.getConnUrl());
		this.setPassword(type.getUserPass());
	}

	private synchronized void createDataBase() {
		if (db_has_been_created) {
			return;
		}
		log.info("JTesterDataSource:begin create db?");
		DbCreator.createDb(type);
		log.info("JTesterDataSource:end create db.");
		if (type.autoExport()) {
			this.export = new DbSchemaExport(type);
			export.export();
		}
		db_has_been_created = true;
	}
}
