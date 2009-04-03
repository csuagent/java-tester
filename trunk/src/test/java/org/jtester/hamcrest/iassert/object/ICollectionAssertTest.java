package org.jtester.hamcrest.iassert.object;

import java.util.Arrays;

import org.jtester.testng.JTester;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ICollectionAssertTest extends JTester {
	@Test
	public void hasItems_test1() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa");
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa", "ccc");
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(Arrays.asList("aaa", "ccc"));
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(new String[] { "aaa", "ccc" });
		want.collection(Arrays.asList(1, 2, 4)).hasItems(new int[] { 1, 4 });

		want.collection(Arrays.asList(1, 2, 4)).hasItems(1, 4);
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasItems_test2() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaad");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasItems_test3() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems("aaa", "ccc", "dddd");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasItems_test4() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(Arrays.asList("aaac"));
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasItems_test5() {
		want.collection(Arrays.asList("aaa", "bbb", "ccc")).hasItems(new String[] { "aaad", "ccc" });
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void hasItems_test6() {
		want.collection(Arrays.asList(1, 2, 4)).hasItems(1, 5);
	}

	@Test
	public void sizeIs() {
		want.collection(Arrays.asList(1, 2, 4)).sizeIs(3);
	}
}
