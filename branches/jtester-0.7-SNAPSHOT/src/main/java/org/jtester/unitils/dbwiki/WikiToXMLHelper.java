package org.jtester.unitils.dbwiki;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 解析wiki中table的工具类
 * 
 * @author darui.wudr
 * 
 */
public class WikiToXMLHelper {
	/**
	 * 解析wiki table中表名称
	 * 
	 * @param meta
	 * @param line
	 */
	public static void parseSchema(final WikiTableMeta meta, final String line) {
		if (!WikiToXMLHelper.isTableSchema(line)) {
			throw new RuntimeException("this line isn't a schema definder,line:" + line);
		}
		String schema = split(line)[1];
		if (StringUtils.isBlank(schema)) {
			throw new RuntimeException("can't parse schema name from line:" + line);
		}
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
			if (StringUtils.isBlank(field)) {
				throw new RuntimeException("there are a blank field name parsed from line:" + line);
			}
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
	 * 
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

	private static final String schema_regex = "\\|\\s*table\\s*\\|.+";

	/**
	 * 判断是否是定义table schema的wiki,格式|table|schema_name|
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isTableSchema(String line) {
		return line.matches(schema_regex);
	}

	private static final String table_regex = "(\\|[^\\|]*)+\\|?";

	/**
	 * 判断文本是否符合wiki表格定义，|text|text|...
	 * 
	 * @param line
	 * @return
	 */
	public static boolean isWikiTable(String line) {
		return line.matches(table_regex);
	}
}
