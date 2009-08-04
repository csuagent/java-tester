package org.jtester.unitils.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.utility.ClazzUtil;
import org.springframework.beans.factory.InitializingBean;

public class JTesterDataSource extends BasicDataSource implements InitializingBean {
	protected static Log log = LogFactory.getLog(JTesterDataSource.class);

	private static boolean db_has_been_created = false;

	private DatabaseType type;

	private DbSchemaExport export;

	public void afterPropertiesSet() throws Exception {
		init();
	}

	private void init() {
		this.type = DatabaseType.type();

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
		if (ClazzUtil.doesImportSchemaExport() && type.autoExport()) {
			this.export = new DbSchemaExport(type);
			export.export();
		}
		db_has_been_created = true;
	}
}
