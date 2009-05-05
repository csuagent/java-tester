package org.jtester.unitils.jmock;

import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.bean.ISpeak;
import org.jtester.unitils.jmock.bean.Person;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = { "JTester" })
public class MockTest_Two extends JTester {
	@TestedObject
	private Person person = new Person();
	@Mock
	@InjectIntoByType
	private ISpeak speak;

	public void sayHello() {
		checking(new Je() {
			{
				want.oneOf(speak).say(the.string().contains("darui.wu").wanted());
			}
		});
		person.sayHelloTo("darui.wu");
	}

	public void sayHello_2() {
		checking(new Je() {
			{
				want.oneOf(speak).say(with(the.string().contains("darui.wu")));
			}
		});
		person.sayHelloTo("darui.wu");
	}
}
