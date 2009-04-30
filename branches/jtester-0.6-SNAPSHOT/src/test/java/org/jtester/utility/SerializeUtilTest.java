package org.jtester.utility;

import java.io.File;

import org.jtester.testng.JTester;
import org.jtester.utility.beans.Employee;
import org.jtester.utility.beans.Manager;
import org.testng.annotations.Test;

public class SerializeUtilTest extends JTester {

	@Test
	public void pojoToDat() {
		String filename = "d:/temp.dat";
		want.file(filename).unExists();
		SerializeUtil.pojoToDat(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "pojoToDat" })
	public void pojoFromDat() {
		String filename = "d:/temp.dat";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.pojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");

		File file = new File(filename);
		file.delete();
	}

	@Test
	public void pojoFromDat_classpath() {
		String filename = "classpath:org/jtester/utility/manager.dat";
		Manager manager = SerializeUtil.pojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void pojoToxml() {
		String filename = "d:/temp.xml";
		want.file(filename).unExists();
		SerializeUtil.pojoToXml(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "pojoToxml" })
	public void pojoFromXml() {
		String filename = "d:/temp.xml";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.pojoFromXml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");

		File file = new File(filename);
		file.delete();
	}

	@Test
	public void pojoFromXml_classpath() {
		String filename = "classpath:org/jtester/utility/manager.xml";
		Manager manager = SerializeUtil.pojoFromXml(Manager.class, filename);
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
