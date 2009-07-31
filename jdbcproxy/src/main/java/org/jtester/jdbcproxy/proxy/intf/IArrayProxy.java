package org.jtester.jdbcproxy.proxy.intf;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.Map;

import nl.griffelservices.proxy.Proxy;

public interface IArrayProxy {

	public static final Method array_m0_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] {});

	public static final Method array_m1_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] { long.class,
			int.class });

	public static final Method array_m2_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] { long.class,
			int.class, Map.class });

	public static final Method array_m3_getArray = Proxy.getMethod(Array.class, "getArray",
			new Class[] { java.util.Map.class });

	public static final Method array_m4_getBaseType = Proxy.getMethod(Array.class, "getBaseType", new Class[] {});

	public static final Method array_m5_getBaseTypeName = Proxy.getMethod(Array.class, "getBaseTypeName",
			new Class[] {});

	public static final Method array_m6_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] {});

	public static final Method array_m7_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] {
			long.class, int.class });

	public static final Method array_m8_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] {
			long.class, int.class, java.util.Map.class });

	public static final Method array_m9_getResultSet = Proxy.getMethod(Array.class, "getResultSet",
			new Class[] { java.util.Map.class });

}
