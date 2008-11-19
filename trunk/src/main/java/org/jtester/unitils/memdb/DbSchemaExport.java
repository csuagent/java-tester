package org.jtester.unitils.memdb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;

public class DbSchemaExport {
	private static Log log = LogFactory.getLog(DbSchemaExport.class);

	private SchemaExport export;

	private DbSupport dbSupport;

	public DbSchemaExport() {
		this(MemDbType.H2DB);
	}

	public DbSchemaExport(MemDbType type) {
		Configuration cfg = MemDbConfigUtil.getConfig();
		this.export = new SchemaExport(cfg);

		this.dbSupport = MemDbConfigUtil.getDbSupport(type);
		SQLHandler sqlHandler = new DefaultSQLHandler(MemDbConfigUtil.getDataSource());
		dbSupport.init(cfg.getProperties(), sqlHandler, type.getSchemas());
	}

	public void export() {
		log.info("call hibernate tool:org.hibernate.tool.hbm2ddl.SchemaExport.create");
		this.export.execute(MemDbConfigUtil.isScript(), true, false, true);
		log.info("remove foreign key constraints");
		dbSupport.disableReferentialConstraints();
		log.info("remove not null constraints");
		dbSupport.disableValueConstraints();
	}

	protected void removeForeignKeyConstraints() {

		// Set<String> tableNames = dbSupport.getTableNames();
		// for (String tableName : tableNames) {
		// Set<String> constraintNames =
		// dbSupport.getForeignKeyConstraintNames(tableName);
		// for (String constraintName : constraintNames) {
		// dbSupport.removeForeignKeyConstraint(tableName, constraintName);
		// }
		// }
	}

	protected void removeNotNullConstraints() {

		// Set<String> tableNames = dbSupport.getTableNames();
		// for (String tableName : tableNames) {
		// Set<String> primaryKeyColumnNames =
		// dbSupport.getPrimaryKeyColumnNames(tableName);
		//
		// Set<String> notNullColumnNames =
		// dbSupport.getNotNullColummnNames(tableName);
		// for (String notNullColumnName : notNullColumnNames) {
		// if (primaryKeyColumnNames.contains(notNullColumnName)) {
		// continue;
		// }
		// dbSupport.removeNotNullConstraint(tableName, notNullColumnName);
		// }
		// }
	}
}
