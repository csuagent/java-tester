package org.jtester.utility;

import java.util.Date;

import org.jtester.testng.JTester;
import org.jtester.utility.beans.Employee;
import org.jtester.utility.beans.Manager;
import org.jtester.utility.beans.PhoneNumber;
import org.testng.annotations.Test;

public class SerializeUtilTest extends JTester {

	@Test
	public void pojoToDat() {
		String filename = "d:/manager.dat";
		// want.file(filename).unExists();
		SerializeUtil.pojoToDat(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "pojoToDat" })
	public void pojoFromDat() {
		String filename = "d:/manager.dat";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.pojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void pojoFromDat_classpath() {
		String filename = "classpath:org/jtester/utility/manager.dat";
		Manager manager = SerializeUtil.pojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester").propertyEq("phoneNumber.number", "0571-88886666");
		want.date(manager.getDate()).yearIs(2009).monthIs("04").hourIs(16);
	}

	@Test
	public void pojoToxml() {
		String filename = "d:/manager.xml";
		// want.file(filename).unExists();
		SerializeUtil.pojoToXml(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "pojoToxml" })
	public void pojoFromXml() {
		String filename = "d:/manager.xml";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.pojoFromXml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void pojoFromXml_classpath() {
		String filename = "classpath:org/jtester/utility/manager.xml";
		Manager manager = SerializeUtil.pojoFromXml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester").propertyEq("phoneNumber.number", "0571-88886666");
		want.date(manager.getDate()).yearIs(2009).monthIs("04").hourIs(16);
	}

	private Manager mock() {
		Employee harry = new Employee("Harry Hacker", 50000);
		Manager manager = new Manager("Tony Tester", 80000);
		PhoneNumber phone = new PhoneNumber(571, "0571-88886666");
		manager.setSecretary(harry);
		manager.setPhoneNumber(phone);
		manager.setDate(new Date());
		return manager;
	}
}
