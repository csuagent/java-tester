package org.jtester.unitils.dbwiki;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class DataWikiUnitilsTest extends JTester {
	@Test
	public void test() {
		DataWikiUnitils wiki = new DataWikiUnitils();
		String xml = wiki.wiki(this.getClass(), "test-data.wiki");
		System.out.println(xml);
	}
}
