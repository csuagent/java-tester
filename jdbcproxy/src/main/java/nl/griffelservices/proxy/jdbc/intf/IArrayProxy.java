package nl.griffelservices.proxy.jdbc.intf;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.Map;

import nl.griffelservices.proxy.Proxy;

public interface IArrayProxy {
	/**
	 * public abstract java.lang.Object Array.getArray() throws SQLException
	 */
	public static final Method m0_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] {});
	/**
	 * public abstract java.lang.Object Array.getArray(long,int) throws
	 * SQLException
	 */
	public static final Method m1_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] { long.class,
			int.class });
	/**
	 * public abstract java.lang.Object Array.getArray(long,int,java.util.Map)
	 * throws SQLException
	 */
	public static final Method m2_getArray = Proxy.getMethod(Array.class, "getArray", new Class[] { long.class,
			int.class, Map.class });
	/**
	 * public abstract java.lang.Object Array.getArray(java.util.Map) throws
	 * SQLException
	 */
	public static final Method m3_getArray = Proxy.getMethod(Array.class, "getArray",
			new Class[] { java.util.Map.class });
	/**
	 * public abstract int Array.getBaseType() throws SQLException
	 */
	public static final Method m4_getBaseType = Proxy.getMethod(Array.class, "getBaseType", new Class[] {});
	/**
	 * public abstract java.lang.String Array.getBaseTypeName() throws
	 * SQLException
	 */
	public static final Method m5_getBaseTypeName = Proxy.getMethod(Array.class, "getBaseTypeName", new Class[] {});
	/**
	 * public abstract ResultSet Array.getResultSet() throws SQLException
	 */
	public static final Method m6_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] {});
	/**
	 * public abstract ResultSet Array.getResultSet(long,int) throws
	 * SQLException
	 */
	public static final Method m7_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] { long.class,
			int.class });
	/**
	 * public abstract ResultSet Array.getResultSet(long,int,java.util.Map)
	 * throws SQLException
	 */
	public static final Method m8_getResultSet = Proxy.getMethod(Array.class, "getResultSet", new Class[] { long.class,
			int.class, java.util.Map.class });
	/**
	 * public abstract ResultSet Array.getResultSet(java.util.Map) throws
	 * SQLException
	 */
	public static final Method m9_getResultSet = Proxy.getMethod(Array.class, "getResultSet",
			new Class[] { java.util.Map.class });

}
