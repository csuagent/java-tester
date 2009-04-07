package org.jtester.hamcrest.iassert.object;

import org.jtester.testng.JTester;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class ICollectionAssertTest_HasItem_DataProvider extends JTester {

	@DataProvider(name = "provide_hasitems")
	public Object[][] provideArray() {
		Object[][] result = new Object[4][1];
		result[0][0] = new HasItems(new Integer[] { 1, 2, 3 }, new Integer[] { 1, 2 });
		result[1][0] = new HasItems(new Character[] { 'a', 'b', 'c' }, new Character[] { 'a', 'b' });
		result[2][0] = new HasItems(new Boolean[] { true, false }, new Boolean[] { true });
		result[3][0] = new HasItems(new Double[] { 1.2d, 2.8d, 3.9d }, new Double[] { 1.2d, 3.9d });
		return result;
	}

	@Test(dataProvider = "provide_hasitems")
	public void hasItems(HasItems item) {
		want.array(item.getActual()).hasItems(item.getExpected());
	}

	public static class HasItems {
		private Object[] actual;
		private Object[] expected;

		public HasItems(Object[] actual, Object[] expected) {
			this.actual = actual;
			this.expected = expected;
		}

		public Object[] getActual() {
			return actual;
		}

		public void setActual(Object[] actual) {
			this.actual = actual;
		}

		public Object[] getExpected() {
			return expected;
		}

		public void setExpected(Object[] expected) {
			this.expected = expected;
		}
	}
}
