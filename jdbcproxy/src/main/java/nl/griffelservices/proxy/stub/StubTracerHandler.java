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
package nl.griffelservices.proxy.stub;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.TreeMap;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.Proxy;

import org.jtester.jdbcproxy.stub.FileStubTracerMerger;

/**
 * This handler is meant to save stub information for the calls to the proxied
 * interfaces. It passes all proxy calls to another implementation of the
 * interfaces while logging all calls (including parameters and return values,
 * but not exceptions) in a form suitable for {@link StubTracerMerger}. The
 * actual logging is done by a {@link StubTracer} class. This driver uses the
 * other implementation of the interface as the proxy data.
 * 
 * @author Frans van Gool
 */
public class StubTracerHandler implements Handler {
	private final static boolean merge = false;
	/**
	 * maps the proxy data (i.e. other implementation of the interface) to the
	 * {@link ProxyObject} that contains the stub data
	 */
	private HashMap<Object, ProxyObject> proxyOriginalToStubMap;
	/** The object that does the actual logging of the stub data */
	private StubTracer tracer;

	public static TreeMap<ProxyIdentity, RequestResponse> map = new TreeMap<ProxyIdentity, RequestResponse>();

	/**
	 * Constructs a StubTracerHandler object.
	 * 
	 * @param tracer
	 *            the object that does the actual logging of the stub data
	 */
	public StubTracerHandler(StubTracer tracer) {
		this.tracer = tracer;
		proxyOriginalToStubMap = new HashMap<Object, ProxyObject>();
	}

	public void init(Class<?> proxyClass, Object proxyObject) {
		// Do nothing
	}

	public Object invoke(Proxy proxy, Method method, Object[] parameters) throws Exception {
		Object value = method.invoke(proxy.getProxyObject(), parameters);
		Class<?> returnType = proxy.narrowReturnType(method.getReturnType(), value);
		Object valueProxy = proxy.getReturnValueProxy(returnType, this, value);

		Object proxyObject = proxy.getProxyObject();
		ProxyObject stub = getStub(proxyObject, proxy.narrowReturnType(Object.class, proxyObject));
		String newStatus = String.valueOf(Integer.parseInt(stub.getProxyStatus()) + 1);
		Request.EqualityParameter equalityParameters[] = new Request.EqualityParameter[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			equalityParameters[i] = new Request.EqualityParameter(parameters[i] == null ? "" : parameters[i].toString());
		}
		Request request = new Request(stub.getProxyId(), new Request.EqualityParameter(stub.getProxyStatus()), method,
				equalityParameters);
		Response response = new Response(newStatus, value != valueProxy ? getStub(value, returnType) : value);

		if (merge) {// for generate merger file by xufangbj@cn.ibm.com
			new FileStubTracerMerger(map, request, response);
		} else {// for generate request/response files
			tracer.trace(request, response);
		}

		stub.setProxyStatus(newStatus);

		return valueProxy;
	}

	/**
	 * Gets the stub from {@link #proxyOriginalToStubMap} that has the given
	 * proxy data as key. If no such stub exists, then construct one, add it to
	 * {@link #proxyOriginalToStubMap} and return it.
	 * 
	 * @param proxyObject
	 *            the proxy data (i.e. other implementation of the interface)
	 * @param proxyClass
	 *            the (narrowed) return type of the method call (needed only if
	 *            a stub needs to be constructed)
	 * @return the desired stub from {@link #proxyOriginalToStubMap}
	 * @see Proxy#narrowReturnType(Class, Object)
	 */
	private ProxyObject getStub(Object proxyObject, Class<?> proxyClass) {
		ProxyObject stub = (ProxyObject) proxyOriginalToStubMap.get(proxyObject);
		if (stub == null) {
			stub = new ProxyObject(proxyClass, String.valueOf(proxyOriginalToStubMap.size()), "0", null);
			proxyOriginalToStubMap.put(proxyObject, stub);
		}
		return stub;
	}
}
