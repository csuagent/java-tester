/*
 * Created on 2007-12-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jtester.jdbcproxy.stub;

import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.RequestEncoder;
import nl.griffelservices.proxy.stub.Response;
import nl.griffelservices.proxy.stub.ResponseDecoder;
import nl.griffelservices.proxy.stub.Stub;

/**
 * @author xufangbj@cn.ibm.com
 * 
 *         This stub gets the responses from a File in local. Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class FileStub extends FileClient implements Stub {
	/** the request encoder */
	private static final RequestEncoder encoder = new RequestEncoder();
	/** the response decoder */
	private static final ResponseDecoder decoder = new ResponseDecoder();

	/**
	 * Constructs a HttpStub object.
	 * 
	 * @param host
	 *            the hostname of the HTTP server that provides the stub
	 *            information
	 * @param port
	 *            the port of the HTTP server that provides the stub information
	 * @param timeout
	 *            the timeout that will be used for HTTP requests to the HTTP
	 *            server that provides the stub information
	 */
	public FileStub(String filename) {
		super(filename);
	}

	public Response invoke(Request request) throws Exception {
		String requestStr = encoder.encode(request);
		String responseStr = invoke(requestStr);
		Response response = decoder.decode(responseStr);
		return response;
	}
}
