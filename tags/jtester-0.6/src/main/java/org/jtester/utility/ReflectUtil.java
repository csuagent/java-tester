package org.jtester.utility;

import java.lang.reflect.Field;

/**
 * POJO反射处理工具类
 * 
 * @author darui.wudr
 * 
 */
public class ReflectUtil {
	/**
	 * 给对象obj名为fieldname的属性赋值
	 * 
	 * @param obj
	 *            赋值对象
	 * @param fieldName
	 *            赋值属性
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static void setFieldValue(Object obj, String fieldName, Object value) throws SecurityException,
			NoSuchFieldException {
		assert obj != null : "the obj can't be null";

		Field field = obj.getClass().getDeclaredField(fieldName);
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			throw new RuntimeException("Unable to update the value in field[" + field.getName() + "]", e);
		} finally {
			field.setAccessible(accessible);
		}
	}

	/**
	 * 获得对象obj的属性名为fieldname的字段值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static Object getFieldValue(Object obj, String fieldName) throws SecurityException, NoSuchFieldException {
		assert obj != null : "the obj can't be null";
		return getFieldValue(obj.getClass(), obj, fieldName);
	}

	/**
	 * 获得对象obj的属性名为fieldname的字段值
	 * 
	 * @param claz
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public static Object getFieldValue(Class<?> claz, Object obj, String fieldName) throws SecurityException,
			NoSuchFieldException {
		assert obj != null : "the obj can't be null";
		Field field = claz.getDeclaredField(fieldName);
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get the value in field[" + field.getName() + "]", e);
		} finally {
			field.setAccessible(accessible);
		}
	}
}
