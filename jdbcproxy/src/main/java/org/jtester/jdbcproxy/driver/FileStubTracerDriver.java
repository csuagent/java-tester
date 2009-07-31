package org.jtester.jdbcproxy.driver;

import java.sql.DriverManager;

import org.jtester.jdbcproxy.util.FileCombinedHandler;

import nl.griffelservices.proxy.Handler;

/**
 * 
 * @author xufangbj@cn.ibm.com
 */
public class FileStubTracerDriver extends org.jtester.jdbcproxy.proxy.impl.DriverProxy {
	static {
		try {
			DriverManager.registerDriver(new FileStubTracerDriver());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Constructs a new StubTracerDriver object.
	 */
	public FileStubTracerDriver() {
		this(new FileCombinedHandler(1, 0, true), null);
	}

	/**
	 * Constructs a new StubTracerDriver object.
	 * 
	 * @param handler
	 *            the proxy handler
	 * @param proxyObject
	 *            the proxy data
	 */
	public FileStubTracerDriver(Handler handler, Object proxyObject) {
		super(handler, proxyObject);
	}

}
