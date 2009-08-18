package org.jtester.unitils.jmock.expectations;

import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;

@Test(groups = "jtester")
public class ICallMethodTest extends JTester {
	@Mock
	private SomeInterface someInterface;

	public void oneOf() {
		checking(new Je() {
			{
				will.call.oneOf(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
	}

	public void exactly() {
		checking(new Je() {
			{
				will.call.exactly(2).of(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
	}

	public void allowing() {
		checking(new Je() {
			{
				will.call.allowing(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
	}

	public void ignoring() {
		checking(new Je() {
			{
				will.call.ignoring(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
	}

	@Test(expectedExceptions = Throwable.class)
	public void never() {
		checking(new Je() {
			{
				will.call.never(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
	}

	public void atLeast() {
		checking(new Je() {
			{
				will.call.atLeast(2).of(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
		this.someInterface.call();
	}

	public void between() {
		checking(new Je() {
			{
				will.call.between(2, 3).of(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
		this.someInterface.call();
	}

	public void atMost() {
		checking(new Je() {
			{
				will.call.atMost(2).of(someInterface).call();
				will.returns.value("call one");
			}
		});
		this.someInterface.call();
		this.someInterface.call();
		// this.someInterface.call();
	}

	public static class SomeInterface {
		public String call() {
			return "test";
		}
	}
}
