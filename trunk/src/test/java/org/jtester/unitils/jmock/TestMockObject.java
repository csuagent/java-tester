package org.jtester.unitils.jmock;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.jmock.Expectations;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.bean.ISay;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class TestMockObject extends JTester {
	@Mock
	private ISay say;

	@Test
	public void testMock1() {
		JmockUnitils.checking(new Expectations() {
			{
				allowing(say).count();
				will(returnValue(3));
			}
		});
		int count = say.count();
		System.out.println(count);
		MatcherAssert.assertThat(count, IsEqual.equalTo(3));
	}

	@Test
	public void testMock2() {
		checking(new JExpectations() {
			{
				allowing(say).count();
				will(returnValue(3));
			}
		});
		int count = say.count();
		System.out.println(count);
		MatcherAssert.assertThat(count, IsEqual.equalTo(3));
	}
}
