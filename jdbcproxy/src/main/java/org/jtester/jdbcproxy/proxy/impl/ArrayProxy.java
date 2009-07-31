package org.jtester.jdbcproxy.proxy.impl;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.jtester.jdbcproxy.proxy.intf.IArrayProxy;

import nl.griffelservices.proxy.Handler;

public class ArrayProxy extends JdbcProxy implements Array, IArrayProxy {

	public ArrayProxy(Handler handler, Object proxyObject) {
		super(handler, Array.class, proxyObject);
	}

	public java.lang.Object getArray() throws SQLException {
		return (java.lang.Object) invoke(array_m0_getArray, new Object[] {});
	}

	public java.lang.Object getArray(long p0, int p1) throws SQLException {
		return (java.lang.Object) invoke(array_m1_getArray, new Object[] { new Long(p0), new Integer(p1) });
	}

	public Object getArray(long p0, int p1, Map<String, Class<?>> map) throws SQLException {
		return (Object) invoke(array_m2_getArray, new Object[] { new Long(p0), new Integer(p1), map });
	}

	public Object getArray(Map<String, Class<?>> map) throws SQLException {
		return (Object) invoke(array_m3_getArray, new Object[] { map });
	}

	public int getBaseType() throws SQLException {
		return ((Integer) invoke(array_m4_getBaseType, new Object[] {})).intValue();
	}

	public java.lang.String getBaseTypeName() throws SQLException {
		return (java.lang.String) invoke(array_m5_getBaseTypeName, new Object[] {});
	}

	public ResultSet getResultSet() throws SQLException {
		return (ResultSet) invoke(array_m6_getResultSet, new Object[] {});
	}

	public ResultSet getResultSet(long p0, int p1) throws SQLException {
		return (ResultSet) invoke(array_m7_getResultSet, new Object[] { new Long(p0), new Integer(p1) });
	}

	public ResultSet getResultSet(long p0, int p1, Map<String, Class<?>> map) throws SQLException {
		return (ResultSet) invoke(array_m8_getResultSet, new Object[] { new Long(p0), new Integer(p1), map });
	}

	public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
		return (ResultSet) invoke(array_m9_getResultSet, new Object[] { map });
	}
}
