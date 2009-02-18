package org.jtester.utility;

import java.lang.reflect.Field;

import ognl.DefaultMemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.unitils.core.UnitilsException;

public class ReflectUtil {
	public static void setFieldValue(Object obj, String fieldName, Object value)
			throws SecurityException, NoSuchFieldException {
		assert obj != null : "the obj can't be null";

		Field field = obj.getClass().getDeclaredField(fieldName);
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			throw new RuntimeException("Unable to update the value in field["
					+ field.getName() + "]", e);
		} finally {
			field.setAccessible(accessible);
		}
	}

	public static Object getFieldValue(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException {
		assert obj != null : "the obj can't be null";
		return getFieldValue(obj.getClass(), obj, fieldName);
	}

	public static Object getFieldValue(Class<?> claz, Object obj,
			String fieldName) throws SecurityException, NoSuchFieldException {
		assert obj != null : "the obj can't be null";
		Field field = claz.getDeclaredField(fieldName);
		boolean accessible = field.isAccessible();
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			throw new RuntimeException("Unable to get the value in field["
					+ field.getName() + "]", e);
		} finally {
			field.setAccessible(accessible);
		}
	}

	public static Object getProperty(Object object, String ognlExpression) {
		try {
			OgnlContext ognlContext = new OgnlContext();
			ognlContext.setMemberAccess(new DefaultMemberAccess(true));
			Object ognlExprObj = Ognl.parseExpression(ognlExpression);
			return Ognl.getValue(ognlExprObj, ognlContext, object);
		} catch (OgnlException e) {
			throw new UnitilsException(
					"Failed to get property value using OGNL expression "
							+ ognlExpression, e);
		}
	}
}
