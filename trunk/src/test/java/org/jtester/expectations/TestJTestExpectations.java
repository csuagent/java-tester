package org.jtester.expectations;

import org.jtester.expectations.service.CalledService;
import org.jtester.expectations.service.CallingService;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;

public class TestJTestExpectations extends JTester {
	@Mock
	private CalledService calledService;

	private CallingService callingService = new CallingService();

	@Test
	public void test1() {
		callingService.setCalledService(calledService);
		checking(new JExpectations() {
			{
				// $.one(calledService).called(the.string().contains("test").$($));
				$.one(calledService).called(with(the.string().contains("test")));
				//$.will(returnValue("dddd"));
			}
		});
		callingService.call("i am a test message!");
	}
}
