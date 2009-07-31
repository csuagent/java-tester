package org.jtester.jdbcproxy.proxy.impl;

import java.sql.Blob;

import nl.griffelservices.proxy.Handler;

import org.jtester.jdbcproxy.proxy.intf.IBlobProxy;

public class BlobProxy extends JdbcProxy implements Blob, IBlobProxy {

	public BlobProxy(Handler handler, Object proxyObject) {
		super(handler, Blob.class, proxyObject);
	}

	public byte[] getBytes(long p0, int p1) throws java.sql.SQLException {
		return (byte[]) invoke(blob_m0_getBytes, new Object[] { new Long(p0), new Integer(p1) });
	}

	public long length() throws java.sql.SQLException {
		return ((Long) invoke(blob_m1_length, new Object[] {})).longValue();
	}

	public long position(Blob p0, long p1) throws java.sql.SQLException {
		return ((Long) invoke(blob_m2_position, new Object[] { p0, new Long(p1) })).longValue();
	}

	public long position(byte[] p0, long p1) throws java.sql.SQLException {
		return ((Long) invoke(blob_m3_position, new Object[] { p0, new Long(p1) })).longValue();
	}

	public java.io.InputStream getBinaryStream() throws java.sql.SQLException {
		return (java.io.InputStream) invoke(blob_m4_getBinaryStream, new Object[] {});
	}

	public java.io.OutputStream setBinaryStream(long p0) throws java.sql.SQLException {
		return (java.io.OutputStream) invoke(blob_m5_setBinaryStream, new Object[] { new Long(p0) });
	}

	public int setBytes(long p0, byte[] p1) throws java.sql.SQLException {
		return ((Integer) invoke(blob_m6_setBytes, new Object[] { new Long(p0), p1 })).intValue();
	}

	public int setBytes(long p0, byte[] p1, int p2, int p3) throws java.sql.SQLException {
		return ((Integer) invoke(blob_m7_setBytes, new Object[] { new Long(p0), p1, new Integer(p2), new Integer(p3) }))
				.intValue();
	}

	public void truncate(long p0) throws java.sql.SQLException {
		invoke(blob_m8_truncate, new Object[] { new Long(p0) });
	}
}
