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

import org.jtester.jdbcproxy.proxy.Proxy;

import nl.griffelservices.proxy.Handler;

/**
 * This handler is meant to provide stub responses for the calls to the proxied
 * interfaces. The actual stub responses are provided by a {@link Stub} class.
 * This driver uses the {@link ProxyObject} as the proxy data. Note that this
 * proxy data already contains pre-configured stub responses for a set of
 * requests. This means that for these request, the {@link Stub} does not need
 * to be invoked.
 * 
 * @author Frans van Gool
 */
public class StubHandler implements Handler {
	/** The object that provides the actual stub responses */
	private Stub stub;

	/**
	 * Constructs a StubHandler object.
	 * 
	 * @param stub
	 *            the object that provides the actual stub responses
	 */
	public StubHandler(Stub stub) {
		this.stub = stub;
	}

	public void init(Class<?> proxyClass, Object proxyObject) {
		// Do nothing;
	}

	public Object invoke(Proxy proxy, Method method, Object[] parameters) throws Exception {
		if (method.getParameterTypes().length != parameters.length) {
			throw new IllegalArgumentException("Number of parameters " + parameters.length + " does not match method "
					+ method);
		}
		ProxyObject proxyObject = (ProxyObject) proxy.getProxyObject();

		// first see whether a response for this call has already been loaded in
		// the proxy object
		for (int i = 0; i < proxyObject.getRequestResponseCount(); i++) {
			if (proxyObject.getRequestResponse(i).getRequest().matches(proxyObject.getProxyId(),
					proxyObject.getProxyStatus(), method, parameters)) {
				return invoke(proxy, method, proxyObject.getRequestResponse(i).getResponse());
			}
		}

		// No response was found in the proxy object, so ask the stub
		Parameter.EqualityParameter equalityParameters[] = new Parameter.EqualityParameter[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			equalityParameters[i] = new Parameter.EqualityParameter(parameters[i].toString());
		}
		Request request = new Request(proxyObject.getProxyId(), new Parameter.EqualityParameter(proxyObject
				.getProxyStatus()), method, equalityParameters);
		return invoke(proxy, method, stub.invoke(request));
	}

	/**
	 * Sets the new proxy status and returns the proxy class for the return
	 * value.
	 * 
	 * @param proxy
	 *            the proxy class whose method is called
	 * @param method
	 *            the method that is called
	 * @param response
	 *            the response for the method call
	 * @return the proxy class for the return value
	 */
	private Object invoke(Proxy proxy, Method method, Response response) {
		((ProxyObject) proxy.getProxyObject()).setProxyStatus(response.getNewStatus());
		if (response.getReturnValue() instanceof ProxyObject) {
			ProxyObject returnValue = (ProxyObject) ((ProxyObject) response.getReturnValue()).clone();
			return proxy.getReturnValueProxy(returnValue.getProxyClass(), this, returnValue);
		} else {
			return proxy.getReturnValueProxy(method.getReturnType(), this, response.getReturnValue());
		}
	}
}
