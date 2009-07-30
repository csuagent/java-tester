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
package org.jtester.jdbcproxy.stub;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import nl.griffelservices.proxy.stub.ProxyIdentity;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.RequestDecoder;
import nl.griffelservices.proxy.stub.RequestEncoder;
import nl.griffelservices.proxy.stub.RequestResponse;
import nl.griffelservices.proxy.stub.Response;
import nl.griffelservices.proxy.stub.ResponseDecoder;
import nl.griffelservices.proxy.stub.ResponseEncoder;
import nl.griffelservices.proxy.stub.StubTracerHandler;

/**
 * Command-line tool that merges the individual request and response files as
 * written by {@link StubTracerHandler} into a single response.
 * 
 * @author xufangbj@cn.ibm.com
 */
public class FileStubTracerMerger {
	/**
	 * pattern that matches request file names and identifies the id and status
	 * in these file names
	 */
	protected static final Pattern requestPattern = Pattern.compile("request_(.+)_(.+)\\.txt");
	/**
	 * pattern that matches response file names and identifies the id and status
	 * in these file names
	 */
	protected static final Pattern responsePattern = Pattern.compile("response_(.+)_(.+)\\.txt");
	/** the request decoder */
	private static final RequestDecoder requestDecoder = new RequestDecoder();
	/** the response decoder */
	private static final ResponseDecoder responseDecoder = new ResponseDecoder();
	/** the response encoder */
	private static final ResponseEncoder responseEncoder = new ResponseEncoder();

	/** maps {@link ProxyIdentity} objects to {@link RequestResponse} objects */
	// private final TreeMap map;
	/** the request encoder */
	private static final RequestEncoder requestEncoder = new RequestEncoder();

	/**
	 * Constructs a FileStubTracerMerger object. Loads all (decoded) request and
	 * response files, but does not merge them yet.
	 * 
	 * @param request
	 *            current request
	 * @param response
	 *            current response
	 * @param map
	 *            the map that request and response are setted
	 * @throws Exception
	 *             if an error occurs
	 */

	public FileStubTracerMerger(TreeMap<ProxyIdentity, RequestResponse> map, Request request, Response response)
			throws Exception {
		String id = request.getDesiredId();
		String status = request.getDesiredStatus().getDesiredValue();
		setRequest(map, new ProxyIdentity(id, status), requestDecoder.decode(requestEncoder.encode(request)));
		setResponse(map, new ProxyIdentity(id, status), responseDecoder.decode(responseEncoder.encode(response)));
	}

	/**
	 * Returns the response that corresponds to the given proxy identity. If the
	 * return value of the response is a {@link ProxyObject}, then all relevant
	 * {@link RequestResponse} objects are added to it. A
	 * {@link RequestResponse} object is relevant if they are loaded (see
	 * {@link #StubTracerMerger(File)}) and the id of the proxy object matches
	 * the id of the request.
	 * 
	 * @param map
	 *            the map that request and response are setted
	 * @param proxyId
	 *            the id of the proxy object for which the response is needed
	 * @param proxyStatus
	 *            the status of the proxy object for which the response is
	 *            needed
	 * @return the expanded response
	 */
	public static Response getResponse(TreeMap<ProxyIdentity, RequestResponse> map, String proxyId, String proxyStatus) {
		ProxyIdentity newproxyidentity = new ProxyIdentity(proxyId, proxyStatus);
		map.get(newproxyidentity);
		Response response = ((RequestResponse) map.get(newproxyidentity)).getResponse();
		String newstatus = response.getNewStatus();
		Object returnvalue = response.getReturnValue();
		List<RequestResponse> values = new ArrayList<RequestResponse>(map.values());
		Object expandereturnvalue = expandReturnValue(1, values, returnvalue);
		Response newresponse = new Response(newstatus, expandereturnvalue);
		return newresponse;

	}

	/**
	 * Sets the given request for the given proxy identity in the {@link #map}.
	 * Any previous request is overwritten.
	 * 
	 * @param map
	 *            the map that request and response are setted
	 * @param pi
	 *            the proxy identity
	 * @param request
	 *            the request
	 */
	private void setRequest(TreeMap<ProxyIdentity, RequestResponse> map, ProxyIdentity pi, Request request) {
		RequestResponse rr = (RequestResponse) map.get(pi);
		if (rr == null) {
			map.put(pi, new RequestResponse(request, null));
		} else {
			map.put(pi, new RequestResponse(request, rr.getResponse()));
		}
	}

	/**
	 * Sets the given response for the given proxy identity in the {@link #map}.
	 * Any previous response is overwritten.
	 * 
	 * @param map
	 *            the map that request and response are setted
	 * @param pi
	 *            the proxy identity
	 * @param response
	 *            the response
	 */
	private void setResponse(TreeMap<ProxyIdentity, RequestResponse> map, ProxyIdentity pi, Response response) {
		RequestResponse rr = (RequestResponse) map.get(pi);
		if (rr == null) {
			map.put(pi, new RequestResponse(null, response));
		} else {
			map.put(pi, new RequestResponse(rr.getRequest(), response));
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

	/**
	 * Adds the relevant {@link RequestResponse} objects to the return value if
	 * that is a {@link ProxyObject}. A {@link RequestResponse} object is
	 * relevant if it can be found in {@link #map} and the id of the proxy
	 * object matches the id of the request.
	 * 
	 * @param returnValue
	 *            the return value
	 * @return the expanded return value
	 */
	private static Object expandReturnValue(int index, Collection<RequestResponse> values, Object returnValue) {
		System.out.println(index);
		if (returnValue instanceof ProxyObject) {
			ProxyObject po = (ProxyObject) returnValue;
			ArrayList<RequestResponse> requestResponseList = new ArrayList<RequestResponse>();
			index++;
			for (RequestResponse rr : values) {
				if (po.getProxyId().equals(rr.getRequest().getDesiredId())) {
					// values.remove(rr);
					Object oo = expandReturnValue(index, values, rr.getResponse().getReturnValue());
					Response response = new Response(rr.getResponse().getNewStatus(), oo);
					requestResponseList.add(new RequestResponse(rr.getRequest(), response));
				}
			}
			RequestResponse requestResponseArray[] = (RequestResponse[]) requestResponseList
					.toArray(new RequestResponse[0]);
			return new ProxyObject(po.getProxyClass(), po.getProxyId(), po.getProxyStatus(), requestResponseArray);
		} else {
			return returnValue;
		}
	}
}
