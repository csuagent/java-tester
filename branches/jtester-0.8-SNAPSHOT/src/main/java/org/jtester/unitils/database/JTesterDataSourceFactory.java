package org.jtester.unitils.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;
import org.unitils.database.config.DataSourceFactory;
import org.unitils.dbmaintainer.structure.ConstraintsDisabler;
import org.unitils.dbmaintainer.util.DatabaseModuleConfigUtils;

public class JTesterDataSourceFactory implements DataSourceFactory {
	protected static Log log = LogFactory.getLog(JTesterDataSourceFactory.class);

	public static final String PROPKEY_DATASOURCE_SCHEMANAMES = "database.schemaNames";

	private DatabaseType type = null;

	public void init(Properties configuration) {
		this.type = DatabaseType.type();
	}

	public DataSource createDataSource() {
		this.checkDoesTestDB();
		BasicDataSource dataSource = new BasicDataSource();
		this.initFactualDataSource(dataSource);
		// this.initRecordProxyDataSource(dataSource);// TODO
		// this.initStubProxyDataSource(dataSource);

		this.doesDisableDataSource(dataSource);
		return dataSource;
	}

	/**
	 * 判断是否本地数据库或者是test数据库<br>
	 * 如果不是返回RuntimeException
	 */
	private void checkDoesTestDB() {
		if (this.type.isMemoryDB() || this.type.getConnUrl().contains("127.0.0.1")
				|| this.type.getConnUrl().contains("localhost")) {
			return;
		}
		String[] schemas = this.type.getSchemas().split(";");
		for (String schma : schemas) {
			if (schma.trim().equals("")) {
				continue;
			} else if (!schma.endsWith("test") && !schma.startsWith("test")) {
				throw new RuntimeException("only local db or test db will be allowed to connect,url:"
						+ this.type.getConnUrl() + ", schemas:" + this.type.getSchemas());
			}
		}
	}

	/**
	 * 是否需要去除数据库的外键约束和字段not null约束
	 * 
	 * @param dataSource
	 */
	protected void doesDisableDataSource(DataSource dataSource) {
		if (!ConfigUtil.doesDisableConstraints()) {
			return;
		}
		log.info("Disables all foreign key and not-null constraints on the configured schema's.");
		SQLHandler handler = new DefaultSQLHandler(dataSource);
		ConstraintsDisabler disabler = DatabaseModuleConfigUtils.getConfiguredDatabaseTaskInstance(
				ConstraintsDisabler.class, ConfigUtil.unitilscfg, handler);
		disabler.disableConstraints();
	}

	/**
	 * 初始化实际的数据库连接
	 * 
	 * @param dataSource
	 */
	protected void initFactualDataSource(BasicDataSource dataSource) {
		log.info("Creating data source. Driver: " + type.getDriveClass() + ", url: " + type.getConnUrl() + ", user: "
				+ type.getUserName() + ", password: <not shown>");
		dataSource.setDriverClassName(type.getDriveClass());
		dataSource.setUsername(type.getUserName());
		dataSource.setPassword(type.getUserPass());
		dataSource.setUrl(type.getConnUrl());
	}

	/**
	 * 初始化JDBCProxy的录制脚本的数据库连接
	 * 
	 * @param dataSource
	 */
	protected void initRecordProxyDataSource(BasicDataSource dataSource) {
		log.info("Creating data source. Driver: " + type.getDriveClass() + ", url: " + type.getConnUrl() + ", user: "
				+ type.getUserName() + ", password: <not shown>");

		dataSource.setDriverClassName("nl.griffelservices.proxy.jdbc.oracle.StubTracerDriver");
		dataSource.setUsername(type.getUserName());
		dataSource.setPassword(type.getUserPass());
		String url = String.format("jdbc:stubtracer:%s:%s:%s", "output", type.getDriveClass(), type.getConnUrl());
		dataSource.setUrl(url);
	}

	/**
	 * 初始化JDBCProxy回放脚本的Stub数据库连接
	 * 
	 * @param dataSource
	 */
	protected void initStubProxyDataSource(BasicDataSource dataSource) {
		log.info("Creating data source. Driver: " + type.getDriveClass() + ", url: " + type.getConnUrl() + ", user: "
				+ type.getUserName() + ", password: <not shown>");

		dataSource.setDriverClassName("org.jtester.jdbcproxy.driver.FileStubTracerDriver");
		dataSource.setUsername(type.getUserName());
		dataSource.setPassword(type.getUserPass());
		String url = "jdbc:stub:output/mergerfile.xml";// TODO
		dataSource.setUrl(url);
	}
}
