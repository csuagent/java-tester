package org.jtester.expectations;

import org.jtester.expectations.service.CalledService;
import org.jtester.expectations.service.CallingService;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = { "JTester" })
public class TestJTestExpectations extends JTester {
	@Mock
	@InjectIntoByType
	private CalledService calledService;

	@TestedObject
	private CallingService callingService = new CallingService();

	@Test
	public void test1() {
		checking(new Je() {
			{
				$.one(calledService).called($.with(the.string().contains("test")));
				$.will(returnValue("dddd"));
				$.ignoring(calledService).called($.with(the.string().any()));
				$.will(returnValue("dddd"));
			}
		});
		callingService.call("i am a test message!");
	}
}
