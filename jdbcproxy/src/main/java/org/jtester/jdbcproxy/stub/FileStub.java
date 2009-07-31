/*
 * Created on 2007-12-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.jtester.jdbcproxy.stub;

import java.util.TreeMap;

import nl.griffelservices.proxy.stub.ProxyIdentity;
import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.RequestEncoder;
import nl.griffelservices.proxy.stub.RequestResponse;
import nl.griffelservices.proxy.stub.Response;
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
	private static final ResponseTreeDecoder decoder = new ResponseTreeDecoder();

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

	private TreeMap<ProxyIdentity, RequestResponse> map = null;

	public Response invoke(Request request) throws Exception {
		String requestStr = encoder.encode(request);
		String responseStr = invoke(requestStr);
		if (map == null) {
			map = decoder.decode(responseStr);
		}
		ProxyIdentity proxyIdentity = request.getProxyIdentity();
		RequestResponse rr = map.get(proxyIdentity);
		if (rr == null) {
			throw new RuntimeException("no reponse for request:" + request.toString());
		}
		return rr.getResponse();
	}
}
