package org.jtester.jmock;

import org.jtester.jmock.service.CalledService;
import org.jtester.jmock.service.CallingService;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = { "JTester" })
public class ExpectationsUtilTest extends JTester {
	@Mock
	@InjectIntoByType
	private CalledService calledService;

	@TestedObject
	private CallingService callingService = new CallingService();

	@Test(groups = { "broken" }, threadPoolSize = 2, invocationCount = 20)
	public void register() {
		checking(new Je() {
			{
				will.call.one(calledService).called(the.string().contains("test").wanted());
				will.returns.value("dddd");
				// will.call.ignoring(calledService).called(the.string().any().wanted());
				// will.returns.value("dddd");
			}
		});
		callingService.call("i am a test message!");
	}

	@Test
	public void register_SinglThread() {
		checking(new Je() {
			{
				will.call.one(calledService).called(the.string().contains("test").wanted());
				will.returns.value("dddd");
				will.call.ignoring(calledService).called(the.string().any().wanted());
				will.returns.value("dddd");
			}
		});
		callingService.call("i am a test message!");
	}
}
