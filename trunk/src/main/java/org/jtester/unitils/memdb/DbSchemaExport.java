package org.jtester.unitils.memdb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.jtester.unitils.database.DataSourceType;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;

public class DbSchemaExport {
	private static Log log = LogFactory.getLog(DbSchemaExport.class);

	private SchemaExport export;

	private DbSupport dbSupport;

	public DbSchemaExport() {
		this(DataSourceType.H2DB);
	}

	public DbSchemaExport(DataSourceType type) {
		Configuration cfg = MemDbConfigUtil.getConfig();
		this.export = new SchemaExport(cfg);

		this.dbSupport = MemDbConfigUtil.getDbSupport(type);
		SQLHandler sqlHandler = new DefaultSQLHandler(MemDbConfigUtil.getDataSource());
		this.dbSupport.init(cfg.getProperties(), sqlHandler, type.getSchemas());
	}

	public void export() {
		log.info("call hibernate tool:org.hibernate.tool.hbm2ddl.SchemaExport.create");
		this.export.execute(MemDbConfigUtil.isScript(), true, false, true);
		log.info("remove foreign key constraints");
		this.dbSupport.disableReferentialConstraints();
		log.info("remove not null constraints");
		this.dbSupport.disableValueConstraints();
	}
}
