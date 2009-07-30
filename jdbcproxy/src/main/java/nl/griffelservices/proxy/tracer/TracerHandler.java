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
package nl.griffelservices.proxy.tracer;

import java.lang.reflect.Method;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.Proxy;

/**
 * This handler is meant to trace the calls to the proxied interfaces. It passes
 * all proxy calls to another implementation of the interfaces while logging all
 * calls (including parameters, return values and exceptions) in a human
 * readable form. The actual logging is done by a {@link Tracer} class. This
 * driver uses the other implementation of the interface as the proxy data.
 * 
 * @author Frans van Gool
 */
public class TracerHandler implements Handler {
	/** The object that does the actual logging of the trace messages */
	private Tracer tracer;

	/**
	 * Constructs a TracerHandler object.
	 * 
	 * @param tracer
	 *            the object that does the actual logging of the trace messages
	 */
	public TracerHandler(Tracer tracer) {
		this.tracer = tracer;
	}

	public void init(Class<?> proxyClass, Object proxyObject) {
		try {
			tracer.trace(proxyClass.getName() + ".<init>(" + proxyObject + ")");
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object invoke(Proxy proxy, Method method, Object[] parameters) throws Exception {
		String msg = method.getDeclaringClass().getName() + "." + method.getName() + "(";
		for (int i = 0; i < parameters.length; i++) {
			if (i > 0)
				msg += ", ";
			msg += parameters[i];
		}
		msg += ")";

		Object value;
		try {
			value = method.invoke(proxy.getProxyObject(), parameters);
		} catch (Exception e) {
			tracer.trace(msg + " throws " + e);
			throw e;
		}
		if (method.getReturnType().equals(void.class)) {
			tracer.trace(msg);
		} else {
			tracer.trace(msg + " returns " + value);
		}
		return proxy.getReturnValueProxy(proxy.narrowReturnType(method.getReturnType(), value), this, value);
	}
}
