package org.jtester.utility;

import java.util.Collection;

public class ArrayUtil {
	public static boolean isArray(Object o) {
		if (o instanceof char[]) {// char
			return true;
		} else if (o instanceof boolean[]) {// boolean
			return true;
		} else if (o instanceof byte[]) {// byte
			return true;
		} else if (o instanceof short[]) {// short
			return true;
		} else if (o instanceof int[]) {// int
			return true;
		} else if (o instanceof long[]) {// long
			return true;
		} else if (o instanceof float[]) {// float
			return true;
		} else if (o instanceof double[]) {// double
			return true;
		} else if (o instanceof Object[]) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static Object[] convert(Collection coll) {
		return coll.toArray(new Object[0]);
	}

	public static boolean isCollection(Object o) {
		if (o == null) {
			return false;
		}
		return o instanceof Collection<?>;
	}

	public static int sizeOf(Object o) {
		if (o == null) {
			return 0;
		}
		int size = 0;
		if (o instanceof Collection<?>) {
			size = ((Collection<?>) o).size();
		} else if (o instanceof char[]) {// char
			size = ((char[]) o).length;
		} else if (o instanceof boolean[]) {// boolean
			size = ((boolean[]) o).length;
		} else if (o instanceof byte[]) {// byte
			size = ((byte[]) o).length;
		} else if (o instanceof short[]) {// short
			size = ((short[]) o).length;
		} else if (o instanceof int[]) {// int
			size = ((int[]) o).length;
		} else if (o instanceof long[]) {// long
			size = ((long[]) o).length;
		} else if (o instanceof float[]) {// float
			size = ((float[]) o).length;
		} else if (o instanceof double[]) {// double
			size = ((double[]) o).length;
		} else if (o instanceof Object[]) {
			size = ((Object[]) o).length;
		} else {
			return 1;
		}

		return size;
	}
}
