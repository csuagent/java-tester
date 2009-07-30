/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is JdbcProxy.
 * 
 * The Initial Developer of the Original Code is Frans van Gool.
 * Portions created by the Initial Developer are Copyright (C) 2006
 * Frans van Gool. All Rights Reserved.
 * 
 * Contributor(s): Frans van Gool.
 * 
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU Lesser General Public License (the �LGPL License�), in which case the
 * provisions of LGPL License are applicable instead of those above. If you wish
 * to allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under the MPL,
 * indicate your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do not
 * delete the provisions above, a recipient may use your version of this file
 * under either the MPL or the LGPL License.
 */
package nl.griffelservices.proxy.jdbc.util;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.Properties;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.Proxy;
import nl.griffelservices.proxy.stub.FolderStubTracer;
import nl.griffelservices.proxy.stub.HttpStub;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.ResponseDecoder;
import nl.griffelservices.proxy.stub.StubHandler;
import nl.griffelservices.proxy.stub.StubTracerHandler;
import nl.griffelservices.proxy.tracer.FileTracer;
import nl.griffelservices.proxy.tracer.TracerHandler;

/**
 * This handler combines the functionality of the {@link TracerHandler}, the
 * {@link StubTracerHandler} and the {@link StubHandler}. The handler itself
 * implements the methods of the <code>java.sql.Driver</code> class and for the
 * other JDBC interfaces it passes control to one of the three other handlers.
 * Which other handler gets the control depends on the driver URL that is used.
 * The following driver URLs are supported:
 * <ul>
 * <li><code>jdbc:tracer:[filename]:[driver]:[driverURL]</code>
 * <ul>
 * <li><code>[filename]</code> is the name of the file to which the trace
 * information must be written. If this element is empty, then the trace
 * information is logged to <code>System.out</code>.
 * <li><code>[driver]</code> is the name of the actual JDBC driver to which the
 * calls will be passed.
 * <li><code>[driverURL]</code> is the URL that must be passed to the actual
 * JDBC driver.
 * </ul>
 * <li><code>jdbc:stubtracer:[foldername]:[driver]:[driverURL]</code>
 * <ul>
 * <li><code>[foldername]</code> is the name of the folder to which the stub
 * information must be written. If this element is empty, then the stub
 * information is logged to <code>System.out</code>.
 * <li><code>[driver]</code> is the name of the actual JDBC driver to which the
 * calls will be passed.
 * <li><code>[driverURL]</code> is the URL that must be passed to the actual
 * JDBC driver.
 * </ul>
 * <li><code>jdbc:stub:[host]:[port]:[timeout]:[driverstubdata]</code>
 * <ul>
 * <li><code>[host]</code> is the hostname of the HTTP server that provides the
 * stub information.
 * <li><code>[port]</code> is the port of the HTTP server that provides the stub
 * information.
 * <li><code>[timeout]</code> is the timeout that will be used for HTTP requests
 * to the HTTP server that provides the stub information.
 * <li><code>[driverstubdata]</code> is the optional filename of a response file
 * that can provide the proxy data for the stub driver. If this field is empty
 * (or not present), then
 * <code>new ProxyObject(Driver.class, "0", "0", null)</code> is used as the
 * proxy data for the stub driver. If this field is not empty, then the
 * <code>returnvalue</code> in the response file, which must be a
 * {@link ProxyObject}, will be used for the stub driver. The
 * <code>newstatus</code> in the response file will be ignored.
 * </ul>
 * </ul>
 * Note that the <code>:</code> character is used to separate the parts in the
 * driver URL. This implies that the individual parts in the driver URL, except
 * for the last part, should not contain that character.
 * 
 * @author Frans van Gool
 */
public class CombinedHandler implements Handler {
	/** the response decoder */
	private static final ResponseDecoder responseDecoder = new ResponseDecoder();

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
	public CombinedHandler(int majorVersion, int minorVersion, boolean isJdbcCompliant) {
		this.majorVersion = new Integer(majorVersion);
		this.minorVersion = new Integer(minorVersion);
		this.isJdbcCompliant = new Boolean(isJdbcCompliant);
	}

	public void init(Class<?> proxyClass, Object proxyObject) {
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
	 * @author Frans van Gool
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
					parts = parts[2].split(":", 4);
					if (parts.length >= 3) {
						HttpStub stub = new HttpStub(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
						handler = new StubHandler(stub);
						if (parts.length == 4 && parts[3].length() > 0) {
							proxyObject = (ProxyObject) responseDecoder.decode(readFile(new File(parts[3])))
									.getReturnValue();
						} else {
							proxyObject = new ProxyObject(Driver.class, "0", "0", null);
						}
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
	private static String readFile(File file) throws Exception {
		byte bytes[] = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytes);
		fis.close();
		return new String(bytes);
	}
}
