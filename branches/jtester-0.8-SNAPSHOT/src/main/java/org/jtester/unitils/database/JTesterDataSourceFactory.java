package org.jtester.unitils.database;

import static org.unitils.util.PropertyUtils.getString;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;
import org.unitils.database.config.DataSourceFactory;
import org.unitils.database.config.PropertiesDataSourceFactory;
import org.unitils.dbmaintainer.structure.ConstraintsDisabler;
import org.unitils.dbmaintainer.util.DatabaseModuleConfigUtils;

public class JTesterDataSourceFactory implements DataSourceFactory {
	protected static Log log = LogFactory.getLog(JTesterDataSourceFactory.class);

	public static final String PROPKEY_DATASOURCE_SCHEMANAMES = "database.schemaNames";

	// private String url;

	private String schemaNames;
	/* The name of the <code>java.sql.Driver</code> class. */
	private String driverClassName;

	/* The url of the database. */
	private String databaseUrl;

	/* The database username. */
	private String userName;

	/* The database password. */
	private String password;

	public void init(Properties configuration) {
		this.driverClassName = getString(PropertiesDataSourceFactory.PROPKEY_DATASOURCE_DRIVERCLASSNAME, configuration);
		this.databaseUrl = getString(PropertiesDataSourceFactory.PROPKEY_DATASOURCE_URL, configuration);
		this.userName = getString(PropertiesDataSourceFactory.PROPKEY_DATASOURCE_USERNAME, null, configuration);
		this.password = getString(PropertiesDataSourceFactory.PROPKEY_DATASOURCE_PASSWORD, null, configuration);

		this.schemaNames = getString(PROPKEY_DATASOURCE_SCHEMANAMES, configuration);
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
		if (this.databaseUrl.startsWith("jdbc:h2:mem:test") || this.databaseUrl.contains("jdbc:hsqldb:mem")
				|| this.databaseUrl.contains("127.0.0.1") || this.databaseUrl.contains("localhost")) {
			return;
		}
		String[] schemas = this.schemaNames.split(";");
		for (String schma : schemas) {
			if (schma.trim().equals("")) {
				continue;
			} else if (!schma.endsWith("test") && !schma.startsWith("test")) {
				throw new RuntimeException("only local db or test db will be allowed to connect,url:"
						+ this.databaseUrl + ", schemas:" + this.schemaNames);
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
		log.info("Creating data source. Driver: " + driverClassName + ", url: " + databaseUrl + ", user: " + userName
				+ ", password: <not shown>");
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setUrl(databaseUrl);
	}

	/**
	 * 初始化JDBCProxy的录制脚本的数据库连接
	 * 
	 * @param dataSource
	 */
	protected void initRecordProxyDataSource(BasicDataSource dataSource) {
		log.info("Creating data source. Driver: " + driverClassName + ", url: " + databaseUrl + ", user: " + userName
				+ ", password: <not shown>");

		dataSource.setDriverClassName("nl.griffelservices.proxy.jdbc.oracle.StubTracerDriver");
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		String url = String.format("jdbc:stubtracer:%s:%s:%s", "output", this.driverClassName, this.databaseUrl);
		dataSource.setUrl(url);
	}

	/**
	 * 初始化JDBCProxy回放脚本的Stub数据库连接
	 * 
	 * @param dataSource
	 */
	protected void initStubProxyDataSource(BasicDataSource dataSource) {
		log.info("Creating data source. Driver: " + driverClassName + ", url: " + databaseUrl + ", user: " + userName
				+ ", password: <not shown>");

		dataSource.setDriverClassName("org.jtester.jdbcproxy.driver.FileStubTracerDriver");
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		String url = "jdbc:stub:output/mergerfile.xml";// TODO
		dataSource.setUrl(url);
	}
}
