package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.jtester.utility.ClazzUtil;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	protected static Log log = LogFactory.getLog(JTesterDataSource.class);

	private static boolean db_has_been_created = false;

	private DataSourceType type;

	private DbSchemaExport export;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	private void init() {
		this.type = DataSourceType.type();
		if (type != null) {
			ConfigUtil.setDbUnitConfig(type);
			this.createDataBase();

			this.setUsername(type.getUserName());
			this.setDriverClassName(type.getDriveClass());
			this.setUrl(type.getConnUrl());
			this.setPassword(type.getUserPass());
		} else {
			this.setDriverClassName(ConfigUtil.driverClazzName());
			this.setUsername(ConfigUtil.databaseUserName());
			this.setPassword(ConfigUtil.databasePassword());
			this.setUrl(ConfigUtil.databaseUrl());
		}
	}

	private synchronized void createDataBase() {
		if (db_has_been_created) {
			return;
		}
		if (ClazzUtil.doesImportSchemaExport() && type.autoExport()) {
			this.export = new DbSchemaExport(type);
			export.export();
		}
		db_has_been_created = true;
	}
}
