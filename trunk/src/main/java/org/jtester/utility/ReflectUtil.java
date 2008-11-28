package org.jtester.utility;

import java.lang.reflect.Field;

public class ReflectUtil {
	public static void setFieldValue(Object obj, String fieldName, Object value) {
		assert obj != null : "the obj can't be null";
		
		Field field = null;
		try {
			field = obj.getClass().getDeclaredField(fieldName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
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
}
