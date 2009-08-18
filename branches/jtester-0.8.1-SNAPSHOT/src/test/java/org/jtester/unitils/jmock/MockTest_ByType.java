package org.jtester.unitils.jmock;

import org.jtester.fortest.beans.ISpeak;
import org.jtester.fortest.beans.Person;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = "jtester")
public class MockTest_ByType extends JTester {
	@TestedObject
	private Person person = new Person();

	@Mock(injectInto = "person")
	private ISpeak speak;

	public void sayHello() {
		checking(new Je() {
			{
				will.call.oneOf(speak).say(the.string().contains("darui.wu").wanted());
			}
		});
		person.sayHelloTo("darui.wu");
	}

	public void sayHello_2() {
		checking(new Je() {
			{
				will.call.oneOf(speak).say(with(the.string().contains("darui.wu")));
			}
		});
		person.sayHelloTo("darui.wu");
	}
}
