package org.jtester.jmock;

import org.jtester.beans.Manager;
import org.jtester.testng.JTester;
import org.jtester.unitils.jmock.Mock;
import org.jtester.utility.SerializeUtil;
import org.testng.annotations.Test;

@Test(groups = "jtester")
public class ReturnValueTest extends JTester {
	@Mock
	private ManagerService service;

	public void returnValue() {
		checking(new Je() {
			{
				will.call.one(service).getManager(the.string().any().wanted());
				will.returns.value(Manager.class, SerializeUtil.class, "manager.xml");
			}
		});
		Manager manager = service.getManager("darui.wu");
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	public static class ManagerService {
		public Manager getManager(String name) {
			return null;
		}
	}
}
