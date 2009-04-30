package org.jtester.utility;

import java.io.File;

import org.jtester.testng.JTester;
import org.jtester.utility.beans.Employee;
import org.jtester.utility.beans.Manager;
import org.testng.annotations.Test;

public class SerializeUtilTest extends JTester {

	@Test
	public void encoded2dat() {
		String filename = "d:/temp.dat";
		want.file(filename).unExists();
		SerializeUtil.encoded2dat(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "encoded2dat" })
	public void decoded4dat() {
		String filename = "d:/temp.dat";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.decoded4dat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");

		File file = new File(filename);
		file.delete();
	}

	@Test
	public void decoded4dat_classpath() {
		String filename = "classpath:org/jtester/utility/manager.dat";
		Manager manager = SerializeUtil.decoded4dat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void encoded2xml() {
		String filename = "d:/temp.xml";
		want.file(filename).unExists();
		SerializeUtil.encoded2xml(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "encoded2xml" })
	public void decoded4xml() {
		String filename = "d:/temp.xml";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.decoded4xml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");

		File file = new File(filename);
		file.delete();
	}

	@Test
	public void decoded4xml_classpath() {
		String filename = "classpath:org/jtester/utility/manager.xml";
		Manager manager = SerializeUtil.decoded4xml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester1");
		System.out.println(manager.getDate());
		want.date(manager.getDate()).yearIs(2009).monthIs("04").hourIs(14);
	}

	private Manager mock() {
		Employee harry = new Employee("Harry Hacker", 50000);
		Manager manager = new Manager("Tony Tester", 80000);
		manager.setSecretary(harry);
		return manager;
	}
}
