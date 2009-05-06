package org.jtester.unitils.dbwiki;

import java.util.List;

/**
 * 解析wiki中table的工具类
 * 
 * @author darui.wudr
 * 
 */
public class WikiTableUtil {
	/**
	 * 解析wiki table中表名称
	 * 
	 * @param meta
	 * @param line
	 */
	public static void parseSchema(final WikiTableMeta meta, final String line) {
		String schema = split(line)[1];

		meta.setSchemaName(schema.trim());
	}

	/**
	 * 解析wiki table中字段列表
	 * 
	 * @param meta
	 * @param line
	 */
	public static void parseHeader(final WikiTableMeta meta, final String line) {
		String[] fields = split(line);
		for (String field : fields) {
			meta.addFieldName(field.trim());
		}
	}

	/**
	 * 解析wiki table中字段值
	 * 
	 * @param meta
	 * @param line
	 */
	public static void parseFields(final WikiTableMeta meta, final String line) {
		String[] fields = split(line);
		meta.newFieldLine();
		for (String field : fields) {
			meta.addFieldValue(field.trim());
		}
		meta.endFieldLine();
	}

	/**
	 * 将所有的wiki table合并成一个xml dataset
	 * @param metas
	 * @return
	 */
	public static String parseMetas(final List<WikiTableMeta> metas) {
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version='1.0' encoding='UTF-8'?>");
		xml.append("<dataset>");
		for (WikiTableMeta meta : metas) {
			xml.append(meta.toXmlSnippet());
		}
		xml.append("</dataset>");
		return xml.toString();
	}

	private static String[] split(String line) {
		String _line = line.trim().replaceFirst("\\|", "");
		int length = _line.length() - 1;
		if (_line.lastIndexOf('|') == length) {
			_line = _line.substring(0, length);
		}
		return _line.split("\\|");
	}
}
