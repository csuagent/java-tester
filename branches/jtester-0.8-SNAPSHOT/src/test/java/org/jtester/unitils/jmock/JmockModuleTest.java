package org.jtester.unitils.jmock;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.jmock.Expectations;
import org.jtester.beans.ISpeak;
import org.jtester.jmock.JTesterExpectations;
import org.jtester.testng.JTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class JmockModuleTest extends JTester {
	@Mock
	private ISpeak say;

	@BeforeMethod
	public void before() {
		checking(new Je() {
			{
				will.call.one(say).count();
				will.returns.value(4);
			}
		});
	}

	@Test
	public void mock_test1() {
		JmockUnitils.checking(new Expectations() {
			{
				one(say).count();
				will(returnValue(5));
			}
		});
		int count1 = say.count();
		MatcherAssert.assertThat(count1, IsEqual.equalTo(4));
		int count2 = say.count();
		MatcherAssert.assertThat(count2, IsEqual.equalTo(5));
	}

	@Test
	public void mock_test2() {
		checking(new JTesterExpectations() {
			{
				will.call.allowing(say).count();
				will.returns.value(3);
				// will(returnValue(3));
			}
		});
		int count1 = say.count();
		MatcherAssert.assertThat(count1, IsEqual.equalTo(4));
		int count2 = say.count();
		MatcherAssert.assertThat(count2, IsEqual.equalTo(3));
	}
}
