package org.jtester.hamcrest;

import java.util.Arrays;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

public class TestCollectionAssert extends JTester {
	@Test
	public void test1() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa");
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa", "ccc");
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(Arrays.asList("aaa", "ccc"));
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(new String[] { "aaa", "ccc" });
		want.collection(Arrays.asList(1, 2, 4)).hasItems(new int[] { 1, 4 });

		want.collection(Arrays.asList(1, 2, 4)).hasItems(1, 4);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test2() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaad");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test3() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa", "ccc", "dddd");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test4() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(Arrays.asList("aaac"));
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test5() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(new String[] { "aaad", "ccc" });
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void test6() {
		want.collection(Arrays.asList(1, 2, 4)).hasItems(1, 5);
	}
}
