package org.jtester.hamcrest.iassert.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class BaseAssertTest extends JTester {

	@Test(expectedExceptions = { AssertionError.class })
	public void testClazIs() {
		want.map(new HashMap<String, Object>()).clazIs(String.class);
	}

	public void testClazIs2() {
		want.map(new HashMap<String, Object>()).clazIs(Map.class);
	}
}
