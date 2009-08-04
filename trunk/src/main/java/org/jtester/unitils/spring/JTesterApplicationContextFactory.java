package org.jtester.unitils.spring;

import java.util.ArrayList;
import java.util.List;

import org.jtester.unitils.config.ConfigUtil;
import org.springframework.context.ConfigurableApplicationContext;
import org.unitils.spring.util.ApplicationContextFactory;

public class JTesterApplicationContextFactory implements ApplicationContextFactory {

	public ConfigurableApplicationContext createApplicationContext(final List<String> locations) {
		List<String> t = new ArrayList<String>();
		for (String str : locations) {
			t.add(str);
		}
		boolean replacedSpringDataSource = ConfigUtil.shouldReplacedSpringDataSource();
		if (replacedSpringDataSource) {
			t.add("classpath:/org/jtester/unitils/spring/jtester-datasource.xml");
		}
		// ConfigurableApplicationContext c = new
		// ClassPathXmlApplicationContext(t.toArray(new
		// String[locations.size()]),
		// false);

		ConfigurableApplicationContext c = new JTesterClassPathXmlApplicationContext(t.toArray(new String[locations
				.size()]), false, null);

		return c;
	}
}
