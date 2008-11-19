package org.jtester.core;

import java.util.ArrayList;
import java.util.List;

public class ArrayConvertor {
	// boolean
	// byte
	// char
	// short int long
	// float double
	public static Object[] convert(char values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Character value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(float values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Float value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(long values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Long value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(short values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Short value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(int values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Integer value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(double values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Double value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(boolean values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Boolean value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}

	public static Object[] convert(byte values[]) {
		List<Object> objs = new ArrayList<Object>();
		for (Byte value : values) {
			objs.add(value);
		}
		return objs.toArray();
	}
}
