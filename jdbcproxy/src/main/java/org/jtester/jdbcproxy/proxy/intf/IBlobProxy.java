package org.jtester.jdbcproxy.proxy.intf;

import java.lang.reflect.Method;
import java.sql.Blob;

import org.jtester.jdbcproxy.proxy.Proxy;


public interface IBlobProxy {

	public static final Method blob_m0_getBytes = Proxy.getMethod(Blob.class, "getBytes", new Class[] { long.class,
			int.class });

	public static final Method blob_m1_length = Proxy.getMethod(Blob.class, "length", new Class[] {});

	public static final Method blob_m2_position = Proxy.getMethod(Blob.class, "position", new Class[] { Blob.class,
			long.class });

	public static final Method blob_m3_position = Proxy.getMethod(Blob.class, "position", new Class[] { byte[].class,
			long.class });

	public static final Method blob_m4_getBinaryStream = Proxy.getMethod(Blob.class, "getBinaryStream", new Class[] {});

	public static final Method blob_m5_setBinaryStream = Proxy.getMethod(Blob.class, "setBinaryStream",
			new Class[] { long.class });

	public static final Method blob_m6_setBytes = Proxy.getMethod(Blob.class, "setBytes", new Class[] { long.class,
			byte[].class });

	public static final Method blob_m7_setBytes = Proxy.getMethod(Blob.class, "setBytes", new Class[] { long.class,
			byte[].class, int.class, int.class });

	public static final Method blob_m8_truncate = Proxy.getMethod(Blob.class, "truncate", new Class[] { long.class });
}
