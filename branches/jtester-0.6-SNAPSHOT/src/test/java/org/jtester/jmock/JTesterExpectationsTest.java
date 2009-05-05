package org.jtester.jmock;

import org.jtester.jmock.service.CalledService;
import org.jtester.jmock.service.CallingService;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = { "JTester" })
public class JTesterExpectationsTest extends JTester {
	@Mock
	@InjectIntoByType
	private CalledService calledService;

	@TestedObject
	private CallingService callingService = new CallingService();

	@Test
	public void test1() {
		checking(new Je() {
			{
				will.call.one(calledService).called(the.string().contains("test").wanted());
				will.returns.value("dddd");
				will.call.ignoring(calledService).called(the.string().any().wanted());
				// $.call.atLeast(1).of(calledService).called($.with(the.string().any()));
				will(returnValue("dddd"));
			}
		});
		callingService.call("i am a test message!");
	}
}
