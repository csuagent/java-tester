package org.jtester.hamcrest;

import junit.framework.AssertionFailedError;

import org.jtester.hamcrest.bean.User;
import org.jtester.testng.JTester;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestReflectionAssert extends JTester {
	@Test(expectedExceptions = { AssertionError.class })
	public void test1() {
		User user1 = new User(1, "John", "Doe");
		User user2 = new User(1, "John", "Doe");
		Assert.assertEquals(user1, user2);
	}

	@Test
	public void test2() {
		User user1 = new User(1, "John", "Doe");
		User user2 = new User(1, "John", "Doe");
		want.bean(user1).reflectionEqualTo(user2);
	}

	@Test(expectedExceptions = { AssertionFailedError.class })
	public void test3() {
		User user1 = new User(1, "John", "Doe");
		User user2 = new User(1, "John", "Doe1");
		want.bean(user1).reflectionEqualTo(user2);
	}
}
