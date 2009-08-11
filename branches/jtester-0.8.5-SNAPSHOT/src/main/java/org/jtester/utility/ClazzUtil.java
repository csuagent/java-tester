package org.jtester.utility;

import java.io.File;

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

	public final static String getPackFromClassName(String clazzName) {
		int index = clazzName.lastIndexOf(".");
		String pack = "";
		if (index > 0) {
			pack = clazzName.substring(0, index);
		}
		return pack;
	}

	/**
	 * 根据类名获得package的路径
	 * 
	 * @param clazzName
	 * @return
	 */
	public final static String getPathFromPath(String clazzName) {
		String pack = getPackFromClassName(clazzName);
		return pack.replace(".", String.valueOf(File.separatorChar));
	}

	/**
	 * 根据类名获得package的路径
	 * 
	 * @param clazzName
	 * @return
	 */
	public final static String getPathFromPath(Class<?> clazz) {
		return getPathFromPath(clazz.getName());
	}
}
