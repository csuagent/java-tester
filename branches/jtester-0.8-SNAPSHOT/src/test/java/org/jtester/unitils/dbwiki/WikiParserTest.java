package org.jtester.unitils.dbwiki;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class WikiParserTest extends JTester {
	@Test
	public void test() {
		WikiParser wiki = new WikiParser();
		String xml = wiki.wiki2xml(this.getClass(), "test-data.wiki");
		System.out.println(xml);
	}
}
