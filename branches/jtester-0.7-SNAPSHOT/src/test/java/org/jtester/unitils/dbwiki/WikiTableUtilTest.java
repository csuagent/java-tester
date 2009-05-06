package org.jtester.unitils.dbwiki;

import org.jtester.testng.JTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WikiTableUtilTest extends JTester {
	private WikiTableMeta meta = null;

	@BeforeMethod
	public void setup() {
		this.meta = new WikiTableMeta();
	}

	@Test
	public void parseSchema() {
		want.string(meta.getSchemaName()).isNull();
		WikiTableUtil.parseSchema(meta, "|table|dfd||");
		want.object(meta).propertyEq("schemaName", "dfd");
	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void parseSchema_fail1() {
		want.string(meta.getSchemaName()).isNull();
		WikiTableUtil.parseSchema(meta, "table|dfd||");
	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void parseSchema_fail2() {
		want.string(meta.getSchemaName()).isNull();
		WikiTableUtil.parseSchema(meta, "asdfsda");
	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void parseSchema_fail3() {
		want.string(meta.getSchemaName()).isNull();
		WikiTableUtil.parseSchema(meta, null);
	}
}
