package org.jtester.hamcrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.jtester.hamcrest.bean.Address;
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
		want.object(user1).reflectionEqualTo(user2);
	}

	@Test(expectedExceptions = { AssertionFailedError.class })
	public void test3() {
		User user1 = new User(1, "John", "Doe");
		User user2 = new User(1, "John", "Doe1");
		want.object(user1).reflectionEqualTo(user2);
	}

	@Test
	public void test4() {
		want.object(1).reflectionEqualTo(1L);

		List<Double> myList = new ArrayList<Double>();
		myList.add(1.0);
		myList.add(2.0);
		want.object(myList).reflectionEqualTo(Arrays.asList(1, 2));

	}

	@Test
	public void test5() {
		List<Integer> myList = Arrays.asList(3, 2, 1);
		want.object(myList).reflectionEqualTo(Arrays.asList(1, 2, 3), opts.CompMode.LENIENT_ORDER);

		User actualUser = new User("John", "Doe", new Address("First street", "12", "Brussels"));
		User expectedUser = new User("John", null, new Address("First street", null, null));
		// assertReflectionEquals(expectedUser, actualUser, IGNORE_DEFAULTS);
		want.object(actualUser).reflectionEqualTo(expectedUser, opts.CompMode.IGNORE_DEFAULTS);
	}

	@Test
	public void test6() {
		Date actualDate = new Date(44444);
		Date expectedDate = new Date();
		// assertReflectionEquals(expectedDate, actualDate, LENIENT_DATES);
		want.object(actualDate).reflectionEqualTo(expectedDate, opts.CompMode.LENIENT_DATES);

	}

	@Test
	public void test7() {
		User user1 = new User(1, "John", "Doe");
		User user2 = new User("John", "Doe", new Address("First street", "", ""));
		// assertPropertyLenientEquals("id", 1, user);
		// assertPropertyLenientEquals("address.street", "First street", user);
		want.object(user1).propertyEqualTo("id", 1);
		want.object(user2).propertyEqualTo("address.street", "First street");
	}

	@Test(expectedExceptions = { AssertionFailedError.class })
	public void test8() {
		User user = new User(1, "John", "Doe");
		want.object(user).propertyEqualTo("id", 2);
	}

	@Test(expectedExceptions = { AssertionFailedError.class })
	public void test9() {
		User user = new User("John", "Doe", new Address("First street", "", ""));
		want.object(user).propertyEqualTo("address.street", "First street1");
	}
}
