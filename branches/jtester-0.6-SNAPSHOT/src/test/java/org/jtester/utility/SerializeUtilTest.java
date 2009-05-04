package org.jtester.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jtester.testng.JTester;
import org.jtester.utility.beans.Employee;
import org.jtester.utility.beans.Manager;
import org.jtester.utility.beans.PhoneNumber;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class SerializeUtilTest extends JTester {

	@Test
	public void setPojoToDat() {
		String filename = "d:/manager.dat";
		// want.file(filename).unExists();
		SerializeUtil.setPojoToDat(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test
	public void setPojoToDat_list() {
		String filename = "d:/managers.dat";
		List<?> list = Arrays.asList(mock(), mock());
		SerializeUtil.setPojoToDat(list, filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "setPojoToDat" })
	public void getPojoFromDat() {
		String filename = "d:/manager.dat";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.getPojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void getPojoFromDat_classpath() {
		String filename = "classpath:org/jtester/utility/manager.dat";
		Manager manager = SerializeUtil.getPojoFromDat(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester").propertyEq("phoneNumber.number", "0571-88886666");
		want.date(manager.getDate()).yearIs(2009).monthIs("04").hourIs(16);
	}

	@Test
	public void getPojoFromDat_list() {
		String filename = "classpath:org/jtester/utility/managers.dat";
		List<?> managers = SerializeUtil.getPojoFromDat(List.class, filename);
		want.collection(managers).sizeEq(2).propertyEq("name", new String[] { "Tony Tester", "Tony Tester" });
	}

	@Test
	public void setPojoToXml() {
		String filename = "d:/manager.xml";
		// want.file(filename).unExists();
		SerializeUtil.setPojoToXml(this.mock(), filename);
		want.file(filename).isExists();
	}

	@Test
	public void setPojoToXml_list() {
		String filename = "d:/managers.xml";
		List<Manager> list = new ArrayList<Manager>();
		list.add(mock());
		list.add(mock());
		SerializeUtil.setPojoToXml(list, filename);
		want.file(filename).isExists();
	}

	@Test(dependsOnMethods = { "setPojoToXml" })
	public void getPojoFromXml() {
		String filename = "d:/manager.xml";
		want.file(filename).isExists();

		Manager manager = SerializeUtil.getPojoFromXml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester");
	}

	@Test
	public void getPojoFromXml_classpath() {
		String filename = "classpath:org/jtester/utility/manager.xml";
		Manager manager = SerializeUtil.getPojoFromXml(Manager.class, filename);
		want.object(manager).propertyEq("name", "Tony Tester").propertyEq("phoneNumber.number", "0571-88886666");
		want.date(manager.getDate()).yearIs(2009).monthIs("04").hourIs(16);
	}

	@Test
	public void getPojoFromXml_list() {
		String filename = "classpath:org/jtester/utility/managers.xml";
		List<?> managers = SerializeUtil.getPojoFromXml(List.class, filename);
		want.collection(managers).sizeEq(2).propertyEq("name", new String[] { "Tony Tester", "Tony Tester" });
	}

	@Test
	public void getPojoFromXml_list2() {
		String filename = "classpath:org/jtester/utility/managers2.xml";
		List<?> managers = SerializeUtil.getPojoFromXml(List.class, filename);
		want.collection(managers).sizeEq(2).propertyEq("name", new String[] { "Tony Tester", "Tony Tester" });
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
