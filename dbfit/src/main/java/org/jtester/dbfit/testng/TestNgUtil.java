package org.jtester.dbfit.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNgUtil {
	public static boolean testng(String clazz, String method) {
		TestNG tng = new TestNG();
		XmlSuite suite = new XmlSuite();
		XmlTest test = new XmlTest(suite);
		test.setName("fitnesse test");
		XmlClass xmlClazz = new XmlClass(clazz);
		xmlClazz.getIncludedMethods().add(method);
		xmlClazz.getExcludedMethods().add(method + ".+");
		test.getXmlClasses().add(xmlClazz);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		tng.setXmlSuites(suites);
		TestListenerAdapter listener = new TestListenerAdapter();
		tng.addListener(listener);
		tng.run();
		int success = listener.getPassedTests().size();
		int failure = listener.getFailedTests().size();
		return success == 1 && failure == 0;
	}
}
