package org.jtester.unitils.database;

import static org.unitils.util.PropertyUtils.getString;

import java.util.Properties;

import javax.sql.DataSource;

import org.unitils.database.config.PropertiesDataSourceFactory;

public class JTesterDataSourceFactory extends PropertiesDataSourceFactory {
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
			return super.createDataSource();
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
		return super.createDataSource();
	}
}
