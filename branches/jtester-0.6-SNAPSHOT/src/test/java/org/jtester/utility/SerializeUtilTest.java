package org.jtester.utility;

import java.io.File;

import org.jtester.testng.JTester;
import org.jtester.utility.beans.Employee;
import org.jtester.utility.beans.Manager;
import org.testng.annotations.Test;

public class SerializeUtilTest extends JTester {

	@Test
	public void testSerialize() {
		String filename = "d:/temp.dat";
		want.file(filename).unExists();
		Employee harry = new Employee("Harry Hacker", 50000);
		Manager manager = new Manager("Tony Tester", 80000);
		manager.setSecretary(harry);

		SerializeUtil.serialize(manager, filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "testSerialize" })
	public void testDeSerialize() {
		String filename = "d:/temp.dat";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.deSerialize(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");

		File file = new File(filename);
		file.delete();
	}
}
