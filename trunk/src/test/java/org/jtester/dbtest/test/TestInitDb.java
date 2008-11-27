package org.jtester.dbtest.test;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project-placeholder.xml",
		"classpath:/org/jtester/dbtest/spring/project-datasource.xml" })
public class TestInitDb extends JTester {

	@Test
	public void testInitDb() {

	}
}
