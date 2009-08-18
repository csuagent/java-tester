package org.jtester.unitils.dbwiki;

import org.jtester.exception.JTesterException;
import org.jtester.testng.JTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups = { "JTester" })
public class WikiPaserUtilTest extends JTester {
	private WikiTableMeta meta = null;

	@BeforeMethod
	public void setup() {
		this.meta = new WikiTableMeta();
	}

	@Test
	public void parseSchema() {
		want.string(meta.getSchemaName()).isNull();
		WikiPaserUtil.parseSchema(meta, "|table|mytable||");
		want.object(meta).propertyEq("schemaName", "mytable");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void parseSchema_fail1() {
		want.string(meta.getSchemaName()).isNull();
		WikiPaserUtil.parseSchema(meta, "table|mytable||");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void parseSchema_fail2() {
		want.string(meta.getSchemaName()).isNull();
		WikiPaserUtil.parseSchema(meta, "mytable");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void parseSchema_fail3() {
		want.string(meta.getSchemaName()).isNull();
		WikiPaserUtil.parseSchema(meta, null);
	}

	@Test
	public void parseHeader() {
		want.collection(meta.getFieldNames()).sizeIs(0);
		WikiPaserUtil.parseHeader(meta, "| field1 | field2|field3 |");
		want.collection(meta.getFieldNames()).sizeIs(3).hasItems("field1", "field2", "field3");
	}

	@Test
	public void underlineName() {
		String out1 = WikiPaserUtil.underlineName(" my name ");
		want.string(out1).isEqualTo("my_name");
		String out2 = WikiPaserUtil.underlineName(" mY Name ");
		want.string(out2).isEqualTo("mY_Name");

		String out3 = WikiPaserUtil.underlineName(" my_Name ");
		want.string(out3).isEqualTo("my_Name");
	}

	@Test(expectedExceptions = { AssertionError.class })
	public void underlineName_asserterror() {
		String out2 = WikiPaserUtil.underlineName(" mY Name ");
		want.string(out2).isEqualTo("my_name");
	}

	@Test(expectedExceptions = { JTesterException.class })
	public void underlineName_fail() {
		WikiPaserUtil.underlineName(null);
	}
}
