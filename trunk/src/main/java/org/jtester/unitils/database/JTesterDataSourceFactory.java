package org.jtester.unitils.database;

import static org.unitils.util.PropertyUtils.getString;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.config.ConfigUtil;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.SQLHandler;
import org.unitils.database.config.PropertiesDataSourceFactory;
import org.unitils.dbmaintainer.structure.ConstraintsDisabler;
import org.unitils.dbmaintainer.util.DatabaseModuleConfigUtils;

public class JTesterDataSourceFactory extends PropertiesDataSourceFactory {
	protected static Log log = LogFactory.getLog(JTesterDataSourceFactory.class);

	public static final String PROPKEY_DATASOURCE_SCHEMANAMES = "database.schemaNames";

	private String url;
	private String schemaNames;

	@Override
	public void init(Properties configuration) {
		super.init(configuration);
		this.url = getString(PROPKEY_DATASOURCE_URL, configuration);
		this.schemaNames = getString(PROPKEY_DATASOURCE_SCHEMANAMES, configuration);
	}

	@Override
	public DataSource createDataSource() {
		if (this.url.startsWith("jdbc:h2:mem:test") || this.url.contains("jdbc:hsqldb:mem")
				|| this.url.contains("127.0.0.1") || this.url.contains("localhost")) {
			return this.doesDisableDataSource();
		}
		String[] schemas = this.schemaNames.split(";");
		for (String schma : schemas) {
			if (schma.trim().equals("")) {
				continue;
			} else if (!schma.endsWith("test") && !schma.startsWith("test")) {
				throw new RuntimeException("only local db or test db will be allowed to connect,url:" + this.url
						+ ", schemas:" + this.schemaNames);
			}
		}
		return this.doesDisableDataSource();
	}

	private DataSource doesDisableDataSource() {
		DataSource dataSource = super.createDataSource();
		if (ConfigUtil.doesDisableConstraints()) {
			log.info("Disables all foreign key and not-null constraints on the configured schema's.");
			SQLHandler handler = new DefaultSQLHandler(dataSource);
			ConstraintsDisabler disabler = DatabaseModuleConfigUtils.getConfiguredDatabaseTaskInstance(
					ConstraintsDisabler.class, ConfigUtil.unitilscfg, handler);
			disabler.disableConstraints();
		}
		return dataSource;
	}
}
