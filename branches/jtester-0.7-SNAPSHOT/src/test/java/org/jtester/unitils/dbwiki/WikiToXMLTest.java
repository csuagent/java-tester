package org.jtester.unitils.dbwiki;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class WikiToXMLTest extends JTester {
	@Test
	public void test() {
		WikiToXML wiki = new WikiToXML();
		String xml = wiki.wiki2xml(this.getClass(), "test-data.wiki");
		System.out.println(xml);
	}
}
