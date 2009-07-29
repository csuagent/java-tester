package org.jtester.jdbcproxy.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.Properties;

import org.jtester.jdbcproxy.stub.FileStub;
import org.jtester.jdbcproxy.stub.FileStubHandler;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.Proxy;
import nl.griffelservices.proxy.stub.FolderStubTracer;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.ResponseDecoder;
import nl.griffelservices.proxy.stub.StubHandler;
import nl.griffelservices.proxy.stub.StubTracerHandler;
import nl.griffelservices.proxy.tracer.FileTracer;
import nl.griffelservices.proxy.tracer.TracerHandler;

public class FileCombinedHandler implements Handler {
	/** the response decoder */
	protected static final ResponseDecoder responseDecoder = new ResponseDecoder();

	/**
	 * public abstract boolean java.sql.Driver.acceptsURL(java.lang.String)
	 * throws java.sql.SQLException
	 */
	private static final Method acceptsURL = Proxy.getMethod(Driver.class, "acceptsURL", new Class[] { String.class });
	/**
	 * public abstract java.sql.Connection
	 * java.sql.Driver.connect(java.lang.String,java.util.Properties) throws
	 * java.sql.SQLException
	 */
	private static final Method connect = Proxy.getMethod(Driver.class, "connect", new Class[] { String.class,
			Properties.class });
	/** public abstract int java.sql.Driver.getMajorVersion() */
	private static final Method getMajorVersion = Proxy.getMethod(Driver.class, "getMajorVersion", new Class[] {});
	/** public abstract int java.sql.Driver.getMinorVersion() */
	private static final Method getMinorVersion = Proxy.getMethod(Driver.class, "getMinorVersion", new Class[] {});
	/**
	 * public abstract java.sql.DriverPropertyInfo[]
	 * java.sql.Driver.getPropertyInfo(java.lang.String,java.util.Properties)
	 * throws java.sql.SQLException
	 */
	private static final Method getPropertyInfo = Proxy.getMethod(Driver.class, "getPropertyInfo", new Class[] {
			String.class, Properties.class });
	/** public abstract boolean java.sql.Driver.jdbcCompliant() */
	private static final Method jdbcCompliant = Proxy.getMethod(Driver.class, "jdbcCompliant", new Class[] {});

	/** the value to be returned by java.sql.Driver.getMajorVersion() */
	private Integer majorVersion;
	/** the value to be returned by java.sql.Driver.getMinorVersion() */
	private Integer minorVersion;
	/** the value to be returned by java.sql.Driver.jdbcCompliant() */
	private Boolean isJdbcCompliant;

	/**
	 * Constructs a CombinedHandler object.
	 * 
	 * @param majorVersion
	 *            the value to be returned by java.sql.Driver.getMajorVersion()
	 * @param minorVersion
	 *            the value to be returned by java.sql.Driver.getMinorVersion()
	 * @param isJdbcCompliant
	 *            the value to be returned by java.sql.Driver.jdbcCompliant()
	 */
	public FileCombinedHandler(int majorVersion, int minorVersion, boolean isJdbcCompliant) {
		this.majorVersion = new Integer(majorVersion);
		this.minorVersion = new Integer(minorVersion);
		this.isJdbcCompliant = new Boolean(isJdbcCompliant);
	}

	public void init(Class proxyClass, Object proxyObject) {
		// Do nothing
	}

	public Object invoke(Proxy proxy, Method method, Object[] parameters) throws Exception {
		if (method.getDeclaringClass().equals(Driver.class)) {
			if (method.equals(acceptsURL)) {
				DriverUrl driverUrl = new DriverUrl((String) parameters[0]);
				if (driverUrl.handler == null) {
					return Boolean.FALSE;
				} else {
					proxy.setProxyObject(driverUrl.proxyObject);
					return driverUrl.handler.invoke(proxy, method, new Object[] { driverUrl.proxyUrl });
				}
			}
			if (method.equals(connect)) {
				DriverUrl driverUrl = new DriverUrl((String) parameters[0]);
				if (driverUrl.handler == null) {
					return null;
				} else {
					proxy.setProxyObject(driverUrl.proxyObject);
					return driverUrl.handler.invoke(proxy, method, new Object[] { driverUrl.proxyUrl, parameters[1] });
				}
			}
			if (method.equals(getMajorVersion)) {
				return majorVersion;
			}
			if (method.equals(getMinorVersion)) {
				return minorVersion;
			}
			if (method.equals(getPropertyInfo)) {
				DriverUrl driverUrl = new DriverUrl((String) parameters[0]);
				if (driverUrl.handler == null) {
					return null;
				} else {
					proxy.setProxyObject(driverUrl.proxyObject);
					return driverUrl.handler.invoke(proxy, method, new Object[] { driverUrl.proxyUrl, parameters[1] });
				}
			}
			if (method.equals(jdbcCompliant)) {
				return isJdbcCompliant;
			}
		}
		throw new UnsupportedOperationException(method.toString());
	}

	/**
	 * Decodes the driver URL.
	 * 
	 * @author xufangbj@cn.ibm.com
	 */
	private static class DriverUrl {
		/** the first part of the driver URL */
		private static final String JDBC = "jdbc";
		/** the second part of the driver URL for the {@link TracerHandler} */
		private static final String TRACER = "tracer";
		/** the second part of the driver URL for the {@link StubTracerHandler} */
		private static final String STUBTRACER = "stubtracer";
		/** the second part of the driver URL for the {@link StubHandler} */
		private static final String STUB = "stub";

		/** the handler represented by the driver URL */
		public Handler handler = null;
		/** the proxy data to be used when invoking the handler */
		public Object proxyObject = null;
		/** the proxy URL that must be used for the actual method call */
		public String proxyUrl = null;

		/**
		 * Constructs a DriverUrl object.
		 * 
		 * @param url
		 *            the driver URL
		 * @throws Exception
		 *             if an error occurs
		 */
		public DriverUrl(String url) throws Exception {
			String parts[] = url.split(":", 3);
			if (parts.length == 3 && parts[0].equals(JDBC)) {
				if (parts[1].equals(TRACER)) {
					parts = parts[2].split(":", 3);
					if (parts.length == 3) {
						File file = parts[0].length() == 0 ? null : new File(parts[0]);
						handler = new TracerHandler(new FileTracer(file));
						proxyObject = Class.forName(parts[1]).newInstance();
						proxyUrl = parts[2];
					}
				}
				if (parts[1].equals(STUBTRACER)) {
					parts = parts[2].split(":", 3);
					if (parts.length == 3) {
						File folder = parts[0].length() == 0 ? null : new File(parts[0]);
						handler = new StubTracerHandler(new FolderStubTracer(folder));
						proxyObject = Class.forName(parts[1]).newInstance();
						proxyUrl = parts[2];
					}
				}
				if (parts[1].equals(STUB)) {
					parts = parts[2].split(":", 2);
					if (parts.length == 1) {
						FileStub stub = new FileStub(parts[0]);
						handler = new FileStubHandler(stub);
						proxyObject = new ProxyObject(Driver.class, "0", "0", null);
						proxyUrl = url;
					}
				}
			}
		}
	}

	/**
	 * Reads the complete content of a file into a string.
	 * 
	 * @param file
	 *            the file to read
	 * @return the content of the file
	 * @throws Exception
	 *             if an error occurs
	 */
	protected static String readFile(File file) throws Exception {
		byte bytes[] = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytes);
		fis.close();
		return new String(bytes);
	}
}
