package org.jtester.dbtest.service;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext( { "classpath:/org/jtester/dbtest/spring/project-placeholder.xml",
		"classpath:/org/jtester/dbtest/spring/project-datasource.xml" })
@Test(groups = { "JTester" })
public class InitDbTest extends JTester {

	@Test
	public void testInitDb() {

	}
}
