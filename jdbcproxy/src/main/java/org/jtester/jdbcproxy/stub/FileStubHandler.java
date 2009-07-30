package org.jtester.jdbcproxy.stub;

import java.lang.reflect.Method;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.Proxy;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.Response;
import nl.griffelservices.proxy.stub.Stub;

/**
 * This handler is meant to provide stub responses for the calls to the proxied
 * interfaces. The actual stub responses are provided by a {@link Stub} class.
 * This driver uses the {@link ProxyObject} as the proxy data. Note that this
 * proxy data already contains pre-configured stub responses for a set of
 * requests. This means that for these request, the {@link Stub} does not need
 * to be invoked.
 * 
 * @author xufangbj@cn.ibm.com
 */
public class FileStubHandler implements Handler {
	/** The object that provides the actual stub responses */
	private FileStub stub;

	/**
	 * Constructs a StubHandler object.
	 * 
	 * @param stub
	 *            the object that provides the actual stub responses
	 */
	public FileStubHandler(FileStub stub) {
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
		Request.EqualityParameter equalityParameters[] = new Request.EqualityParameter[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			equalityParameters[i] = new Request.EqualityParameter(parameters[i].toString());
		}
		Request request = new Request(proxyObject.getProxyId(), new Request.EqualityParameter(proxyObject
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
