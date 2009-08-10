package org.jtester.unitils.jmock.expectations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jtester.fortest.beans.ComplexObject;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.testng.annotations.Test;

@Test(groups = "jtester")
public class ReturnValueTest extends JTester {

	public SomeService someService = new SomeService();

	@Mock(injectInto = "someService")
	public SomeInterface someInterface;

	@SuppressWarnings("unchecked")
	public void testMock() {
		checking(new Je() {
			{
				will.call.one(someInterface).someCall(the.string().isEqualTo("darui.wu").wanted(),
						the.collection().sizeEq(0).wanted(List.class), the.map().any().wanted(HashMap.class));
				// will.returns.value(ComplexObject.instance());
				will.returns.value(ComplexObject.class, ReturnValueTest.class, "complex object.xml");

			}
		});
		String result = this.someService.call("darui.wu");
		want.string(result).contains("name=");
	}

	public void factualInvoke() {
		SomeInterface si = new SomeInterfaceImpl();
		ComplexObject so = si.someCall("darui.wu", null, null);
		want.object(so).propertyEq("name", "I am a test");
	}

	public static class SomeService {
		private SomeInterface someInterface;

		public String call(String name) {
			List<String> list = new ArrayList<String>();
			HashMap<String, String> map = new HashMap<String, String>();
			ComplexObject co = this.someInterface.someCall(name, list, map);
			return co.toString();
		}
	}

	public static interface SomeInterface {
		public ComplexObject someCall(String name, List<?> list, HashMap<String, String> map);
	}

	public static class SomeInterfaceImpl implements SomeInterface {
		public ComplexObject someCall(String name, List<?> list, HashMap<String, String> map) {
			return ComplexObject.instance();
		}
	}

}
