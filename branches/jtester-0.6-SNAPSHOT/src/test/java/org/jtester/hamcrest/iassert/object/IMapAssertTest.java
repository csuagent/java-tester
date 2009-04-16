package org.jtester.hamcrest.iassert.object;

import java.util.HashMap;
import java.util.Map;

import org.jtester.testng.JTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class IMapAssertTest extends JTester {
	private Map<String, String> maps = null;

	@BeforeMethod
	public void setup() {
		maps = new HashMap<String, String>();
		maps.put("one", "my first value");
		maps.put("two", "my second value");
		maps.put("three", "my third value");
	}

	public void hasKeys() {
		want.map(maps).hasKeys("one", "two");
		want.map(maps).hasKeys("three");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasKeys_fail1() {
		want.map(maps).hasKeys("one", "four");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasKeys_fail2() {
		want.map(maps).hasKeys("five");
	}

	public void hasValues() {
		want.map(maps).hasValues("my first value", "my third value");
		want.map(maps).hasValues("my second value");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasValues_fail1() {
		want.map(maps).hasValues("unkown", "my third value");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasValues_fail2() {
		want.map(maps).hasValues("unkown");
	}
}
