package org.jtester.unitils.memdb;

import java.util.Set;

import org.unitils.core.dbsupport.DbSupport;

public class H2DbSupport extends DbSupport {
	/**
	 * Creates support for HsqlDb databases.
	 */
	public H2DbSupport() {
		super("h2db");
	}

	@Override
	public void disableReferentialConstraints() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableValueConstraints() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> getColumnNames(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getTableNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getViewNames() {
		// TODO Auto-generated method stub
		return null;
	}

	// /**
	// * Creates support for HsqlDb databases.
	// */
	// public H2DbSupport() {
	// super("h2db");
	// }
	//
	// /**
	// * Returns the names of all tables in the database.
	// *
	// * @return The names of all tables in the database
	// */
	// @Override
	// public Set<String> getTableNames() {
	// return getSQLHandler().getItemsAsStringSet(
	// "select TABLE_NAME from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'TABLE' AND TABLE_SCHEMA = '"
	// + getSchemaName() + "'");
	// }
	//
	// /**
	// * Gets the names of all columns of the given table.
	// *
	// * @param tableName
	// * The table, not null
	// * @return The names of the columns of the table with the given name
	// */
	// @Override
	// public Set<String> getColumnNames(String tableName) {
	// return getSQLHandler().getItemsAsStringSet(
	// "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME = '"
	// + tableName
	// + "' AND TABLE_SCHEMA = '" + getSchemaName() + "'");
	// }
	//
	// /**
	// * Gets the names of all primary columns of the given table.
	// *
	// * @param tableName
	// * The table, not null
	// * @return The names of the primary key columns of the table with the
	// given
	// * name
	// */
	// @Override
	// public Set<String> getPrimaryKeyColumnNames(String tableName) {
	// return getSQLHandler().getItemsAsStringSet(
	// "select COLUMN_NAME from INFORMATION_SCHEMA.INDEXES where PRIMARY_KEY=TRUE AND TABLE_NAME = '"
	// + tableName + "' AND TABLE_SCHEMA = '" + getSchemaName() + "'");
	// }
	//
	// /**
	// * Returns the names of all columns that have a 'not-null' constraint on
	// * them
	// *
	// * @param tableName
	// * The table, not null
	// * @return The set of column names, not null
	// */
	// @Override
	// public Set<String> getNotNullColummnNames(String tableName) {
	// return getSQLHandler().getItemsAsStringSet(
	// "select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS where IS_NULLABLE = 'NO' AND TABLE_NAME = '"
	// + tableName + "' AND TABLE_SCHEMA = '" + getSchemaName() + "'");
	// }
	//
	// /**
	// * Retrieves the names of all the views in the database schema.
	// *
	// * @return The names of all views in the database
	// */
	// @Override
	// public Set<String> getViewNames() {
	// return getSQLHandler().getItemsAsStringSet(
	// "select TABLE_NAME from INFORMATION_SCHEMA.SYSTEM_TABLES where TABLE_TYPE = 'VIEW' AND TABLE_SCHEMA = '"
	// + getSchemaName() + "'");
	// }
	//
	// /**
	// * Retrieves the names of all the sequences in the database schema.
	// *
	// * @return The names of all sequences in the database
	// */
	// @Override
	// public Set<String> getSequenceNames() {
	// return getSQLHandler().getItemsAsStringSet(
	// "select SEQUENCE_NAME from INFORMATION_SCHEMA.SYSTEM_SEQUENCES where SEQUENCE_SCHEMA = '"
	// + getSchemaName() + "'");
	// }
	//
	// /**
	// * Retrieves the names of all the triggers in the database schema.
	// *
	// * @return The names of all triggers in the database
	// */
	// @Override
	// public Set<String> getTriggerNames() {
	// return getSQLHandler().getItemsAsStringSet(
	// "select TRIGGER_NAME from INFORMATION_SCHEMA.SYSTEM_TRIGGERS where TRIGGER_SCHEM = '"
	// + getSchemaName() + "'");
	// }
	//
	// /**
	// * Returns the value of the sequence with the given name. <p/> Note: this
	// * can have the side-effect of increasing the sequence value.
	// *
	// * @param sequenceName
	// * The sequence, not null
	// * @return The value of the sequence with the given name
	// */
	// @Override
	// public long getSequenceValue(String sequenceName) {
	// return getSQLHandler().getItemAsLong(
	// "select START_WITH from INFORMATION_SCHEMA.SYSTEM_SEQUENCES where SEQUENCE_SCHEMA = '"
	// + getSchemaName() + "' and SEQUENCE_NAME = '" + sequenceName + "'");
	// }
	//
	// /**
	// * Sets the next value of the sequence with the given sequence name to the
	// * given sequence value.
	// *
	// * @param sequenceName
	// * The sequence, not null
	// * @param newSequenceValue
	// * The value to set
	// */
	// @Override
	// public void incrementSequenceToValue(String sequenceName, long
	// newSequenceValue) {
	// getSQLHandler().executeUpdate(
	// "alter sequence " + qualified(sequenceName) + " restart with " +
	// newSequenceValue);
	// }
	//
	// /**
	// * Increments the identity value for the specified identity column on the
	// * specified table to the given value.
	// *
	// * @param tableName
	// * The table with the identity column, not null
	// * @param identityColumnName
	// * The column, not null
	// * @param identityValue
	// * The new value
	// */
	// @Override
	// public void incrementIdentityColumnToValue(String tableName, String
	// identityColumnName, long identityValue) {
	// getSQLHandler().executeUpdate(
	// "alter table " + qualified(tableName) + " alter column " +
	// quoted(identityColumnName)
	// + " RESTART WITH " + identityValue);
	// }
	//
	// /**
	// * Returns the foreign key constraint names that are enabled/enforced for
	// * the table with the given name
	// *
	// * @param tableName
	// * The table, not null
	// * @return The set of constraint names, not null
	// */
	// @Override
	// public Set<String> getForeignKeyConstraintNames(String tableName) {
	// return getSQLHandler().getItemsAsStringSet(
	// "select CONSTRAINT_NAME from INFORMATION_SCHEMA.CONSTRAINTS "
	// + "where CONSTRAINT_TYPE = 'REFERENTIAL' AND TABLE_NAME = '" + tableName
	// + "' AND CONSTRAINT_SCHEMA = '" + getSchemaName() + "'");
	// }
	//
	// /**
	// * Disables the constraint with the given name on table with the given
	// name.
	// *
	// * @param tableName
	// * The table with the constraint, not null
	// * @param constraintName
	// * The constraint, not null
	// */
	// @Override
	// public void removeForeignKeyConstraint(String tableName, String
	// constraintName) {
	// getSQLHandler().executeUpdate(
	// "alter table " + qualified(tableName) + " drop constraint " +
	// quoted(constraintName));
	// }
	//
	// /**
	// * Removes the not-null constraint on the specified column and table
	// *
	// * @param tableName
	// * The table with the column, not null
	// * @param columnName
	// * The column to remove constraints from, not null
	// */
	// @Override
	// public void removeNotNullConstraint(String tableName, String columnName)
	// {
	// getSQLHandler().executeUpdate(
	// "alter table " + qualified(tableName) + " alter column " +
	// quoted(columnName) + " set null");
	// }
	//
	// /**
	// * Sequences are supported.
	// *
	// * @return True
	// */
	// @Override
	// public boolean supportsSequences() {
	// return true;
	// }
	//
	// /**
	// * Triggers are supported.
	// *
	// * @return True
	// */
	// @Override
	// public boolean supportsTriggers() {
	// return true;
	// }
	//
	// /**
	// * Identity columns are supported.
	// *
	// * @return True
	// */
	// @Override
	// public boolean supportsIdentityColumns() {
	// return true;
	// }

}
