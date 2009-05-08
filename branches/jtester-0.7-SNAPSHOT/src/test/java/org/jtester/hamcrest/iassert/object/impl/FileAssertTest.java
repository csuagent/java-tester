package org.jtester.hamcrest.iassert.object.impl;

import java.io.File;
import java.util.UUID;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class FileAssertTest extends JTester {
	@Test(expectedExceptions = { AssertionError.class })
	public void isExists_AssertionError() {
		String tmp = System.getProperty("java.io.tmpdir");
		String file = tmp + File.separatorChar + UUID.randomUUID().toString() + ".txt";
		want.file(new File(file)).isExists();
	}

	public void unExists() {
		String tmp = System.getProperty("java.io.tmpdir");
		String file = tmp + File.separatorChar + UUID.randomUUID().toString() + ".txt";
		want.file(new File(file)).unExists();
	}
}
