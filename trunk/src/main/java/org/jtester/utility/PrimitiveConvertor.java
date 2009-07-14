package org.jtester.utility;

import java.util.HashMap;
import java.util.Map;

/**
 * java原生类型的默认值
 * 
 * @author darui.wudr
 * 
 */
public class PrimitiveConvertor {
	private static Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
	static {
		map.put(String.class, "");
		map.put(Integer.class, 0);
		map.put(Short.class, 0);
		map.put(Long.class, 0);
		map.put(Byte.class, 0);
		map.put(Float.class, 0.0f);
		map.put(Double.class, 0.0d);
		map.put(Character.class, '\0');
		map.put(Boolean.class, false);

		map.put(int.class, 0);
		map.put(short.class, 0);
		map.put(long.class, 0);
		map.put(byte.class, 0);
		map.put(float.class, 0.0f);
		map.put(double.class, 0.0d);
		map.put(char.class, '\0');
		map.put(boolean.class, false);
	}

	public static Object value(Class<?> claz) {
		if (map.containsKey(claz)) {
			return map.get(claz);
		} else {
			return null;
		}
	}
}
