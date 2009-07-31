package org.jtester.jdbcproxy.stub;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import nl.griffelservices.proxy.stub.Encoder;
import nl.griffelservices.proxy.stub.Parameter;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.RequestResponse;
import nl.griffelservices.proxy.stub.Response;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 拼装response对象为一段xml字符串
 * 
 * @author darui.wudr
 * 
 */
public class ResponseTreeEncoder extends Encoder {
	public String encode(Response response) throws Exception {
		Document document = newDocument();
		document.appendChild(encodeResponse(document, response));
		String context = transformDocument(document);
		return context.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
	}

	/**
	 * Encodes the given response object into its DOM element representation.
	 * 
	 * @param document
	 *            the document for which the element is meant
	 * @param response
	 *            the response to encode
	 * @return the DOM representation of the response
	 */
	private Element encodeResponse(Document document, Response response) {
		Element responseElement = document.createElement("response");
		responseElement.setAttribute("newstatus", response.getNewStatus());
		// responseElement.appendChild(createElement(document, "newstatus",
		// response.getNewStatus()));
		responseElement.appendChild(encodeObject(document, response.getReturnValue(), "returnvalue"));
		return responseElement;
	}

	/**
	 * Encodes the given object into its DOM element representation. The object
	 * is either a return value of a response, or an array component in such a
	 * return value.
	 * 
	 * @param document
	 *            the document for which the element is meant
	 * @param object
	 *            the object to encode
	 * @param elementName
	 *            the name of the returned element
	 * @return the DOM representation of the object
	 */
	private Element encodeObject(Document document, Object object, String elementName) {
		Element objectElement = document.createElement(elementName);
		Class<?> objectType = object == null ? null : object.getClass();
		if (objectType == null) {
			// do nothing
		} else if (objectType.isArray()) {
			Element arrayElement = document.createElement("array");
			arrayElement.appendChild(createElement(document, "componenttype", objectType.getComponentType().getName()));
			int length = Array.getLength(object);
			for (int i = 0; i < length; i++) {
				arrayElement.appendChild(encodeObject(document, Array.get(object, i), "component"));
			}
			objectElement.appendChild(arrayElement);
		} else if (objectType.equals(boolean.class) || objectType.equals(Boolean.class)) {
			objectElement.appendChild(createElement(document, "boolean", object.toString()));
		} else if (objectType.equals(byte.class) || objectType.equals(Byte.class)) {
			objectElement.appendChild(createElement(document, "byte", object.toString()));
		} else if (objectType.equals(char.class) || objectType.equals(Character.class)) {
			objectElement.appendChild(createElement(document, "char", object.toString()));
		} else if (objectType.equals(double.class) || objectType.equals(Double.class)) {
			objectElement.appendChild(createElement(document, "double", object.toString()));
		} else if (objectType.equals(float.class) || objectType.equals(Float.class)) {
			objectElement.appendChild(createElement(document, "float", object.toString()));
		} else if (objectType.equals(int.class) || objectType.equals(Integer.class)) {
			objectElement.appendChild(createElement(document, "int", object.toString()));
		} else if (objectType.equals(long.class) || objectType.equals(Long.class)) {
			objectElement.appendChild(createElement(document, "long", object.toString()));
		} else if (objectType.equals(short.class) || objectType.equals(Short.class)) {
			objectElement.appendChild(createElement(document, "short", object.toString()));
		} else if (objectType.equals(String.class)) {
			objectElement.appendChild(createElement(document, "java.lang.String", object.toString()));
		} else if (objectType.equals(java.util.Date.class)) {
			objectElement.appendChild(createElement(document, "java.util.Date", String
					.valueOf(((java.util.Date) object).getTime())));
		} else if (objectType.equals(java.sql.Date.class)) {
			objectElement.appendChild(createElement(document, "java.sql.Date", String.valueOf(((java.sql.Date) object)
					.getTime())));
		} else if (objectType.equals(java.sql.Time.class)) {
			objectElement.appendChild(createElement(document, "java.sql.Time", String.valueOf(((java.sql.Time) object)
					.getTime())));
		} else if (objectType.equals(java.sql.Timestamp.class)) {
			objectElement.appendChild(createElement(document, "java.sql.Timestamp", String
					.valueOf(((java.sql.Timestamp) object).getTime())));
		} else if (objectType.equals(java.math.BigInteger.class)) {
			objectElement.appendChild(createElement(document, "java.math.BigInteger", object.toString()));
		} else if (objectType.equals(java.math.BigDecimal.class)) {
			java.math.BigDecimal v = (java.math.BigDecimal) object;
			Element bigDecimalElement = document.createElement("java.math.BigDecimal");
			bigDecimalElement.appendChild(createElement(document, "unscaledvalue", v.unscaledValue().toString()));
			bigDecimalElement.appendChild(createElement(document, "scale", String.valueOf(v.scale())));
			objectElement.appendChild(bigDecimalElement);
		} else if (objectType.equals(java.net.URL.class)) {
			objectElement.appendChild(createElement(document, "java.net.URL", object.toString()));
		} else if (objectType.equals(ProxyObject.class)) {
			ProxyObject proxyObject = (ProxyObject) object;
			Element proxyObjectElement = getProxyElement(document, proxyObject);
			objectElement.appendChild(proxyObjectElement);
		} else {
			throw new IllegalArgumentException(elementName + " element has unsupported type " + object.getClass());
		}

		return objectElement;
	}

	private Element getProxyElement(Document document, ProxyObject proxyObject) {
		Element element = document.createElement("ProxyObject");
		element.setAttribute("id", proxyObject.getProxyId());
		element.setAttribute("status", proxyObject.getProxyStatus());
		element.setAttribute("class", proxyObject.getProxyClass().getName());

		for (RequestResponse response : proxyObject.getRequestResponses()) {
			Element requestResponseElement = document.createElement("requestresponse");
			requestResponseElement.appendChild(encodeRequest(document, response.getRequest()));
			requestResponseElement.appendChild(encodeResponse(document, response.getResponse()));
			element.appendChild(requestResponseElement);
		}
		return element;
	}

	private Element encodeRequest(Document document, Request request) {
		Method method = request.getDesiredMethod();
		Parameter parameters[] = request.getDesiredParameter();

		Element requestElement = document.createElement("request");
		document.appendChild(requestElement);
		requestElement.appendChild(createParameterElement(document, "status", request.getDesiredStatus()));
		requestElement.appendChild(createElement(document, "method", method.getName()));
		for (int i = 0; i < parameters.length; i++) {
			Element parameterElement = document.createElement("parameter");
			parameterElement.appendChild(createElement(document, "class", method.getParameterTypes()[i].getName()));
			if (parameters[i] != null)
				parameterElement.appendChild(createParameterElement(document, "value", parameters[i]));
			requestElement.appendChild(parameterElement);
		}
		return requestElement;
	}
}
