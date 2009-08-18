package org.jtester.unitils.jmock;

import org.jtester.fortest.beans.ISpeak;
import org.jtester.fortest.beans.Person;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

@Test(groups = { "JTester" })
public class JmockModuleTest_Two extends JTester {
	@TestedObject
	private Person person = new Person();
	@Mock
	@InjectIntoByType
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

	@Test
	public void sayHello_ThrowRuntimeException_WithoutWrapBySpring() {
		checking(new Je() {
			{
				will.call.oneOf(speak).say(the.string().contains("darui.wu").wanted());
				will.throwException(new RuntimeException("test"));
			}
		});
		try {
			person.sayHelloTo("darui.wu");
		} catch (Exception e) {
			String message = e.getMessage();
			want.string(message).contains("test");
		}
	}
}
