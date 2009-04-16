package org.jtester.unitils.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jtester.unitils.database.JTesterDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.database.config.DataSourceFactory;

@Deprecated
public class JTesterDataSourceFactory implements DataSourceFactory {
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(JTesterDataSourceFactory.class);

	public JTesterDataSourceFactory() {
	}

	public void init(Properties configuration) {
	}

	public DataSource createDataSource() {
		String[] db = { "classpath:/org/jtester/unitils/database/jtester-datasource.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(db);
		JTesterDataSource datasource = (JTesterDataSource) context.getBean("dataSource");
		return datasource;
	}
}
