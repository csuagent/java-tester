package org.jtester.core;

import java.util.HashMap;
import java.util.Map;

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
	}

	public static <T> T value(Class<T> claz) {
		if (map.containsKey(claz)) {
			return (T) map.get(claz);
		} else {
			return null;
		}
	}
}
