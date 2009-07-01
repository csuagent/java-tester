package org.jtester.utility;

public class ClazzUtil {
	/**
	 * 类路径中是否有 org.hibernate.tool.hbm2ddl.SchemaExport class
	 * 
	 * @return
	 */
	public final static boolean doesImportSchemaExport() {
		try {
			Class.forName("org.hibernate.tool.hbm2ddl.SchemaExport");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}
