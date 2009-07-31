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

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeMap;

import nl.griffelservices.proxy.stub.Decoder;
import nl.griffelservices.proxy.stub.Parameter;
import nl.griffelservices.proxy.stub.ProxyIdentity;
import nl.griffelservices.proxy.stub.ProxyObject;
import nl.griffelservices.proxy.stub.Request;
import nl.griffelservices.proxy.stub.RequestResponse;
import nl.griffelservices.proxy.stub.Response;

import org.w3c.dom.Element;

public class ResponseTreeDecoder extends Decoder {
	private TreeMap<ProxyIdentity, RequestResponse> map = new TreeMap<ProxyIdentity, RequestResponse>();

	public TreeMap<ProxyIdentity, RequestResponse> decode(String response) throws Exception {
		Element root = parse(response).getDocumentElement();

		List<Element> elements = getElements(root);
		for (Element element : elements) {
			ProxyIdentity proxyIdentity = decodedProxyIdentity(element);
			RequestResponse rr = decodeRequestResponse(proxyIdentity, element);
			map.put(proxyIdentity, rr);
		}
		return map;
	}

	private ProxyIdentity decodedProxyIdentity(Element element) throws Exception {
		String id = element.getAttribute("id");
		String status = element.getAttribute("status");
		return new ProxyIdentity(id, status);
	}

	private RequestResponse decodeRequestResponse(ProxyIdentity proxyIdentity, Element element) throws Exception {
		Element requestElement = (Element) element.getElementsByTagName("request").item(0);
		Element responseElement = (Element) element.getElementsByTagName("response").item(0);

		Request request = docodeRequest(proxyIdentity, requestElement);
		Response response = decodeResponse(responseElement);

		return new RequestResponse(request, response);
	}

	private Request docodeRequest(ProxyIdentity proxyIdentity, Element requestElement) throws Exception {
		List<Element> elements = getElements(requestElement);
		String clazzName = getText(getElement(elements, 0, "class"));
		Class<?> proxyClass = classForName(clazzName);
		String methodName = getText(getElement(elements, 1, "method"));
		Class<?> parameterTypes[] = new Class[elements.size() - 2];
		Parameter parameterValues[] = new Parameter[elements.size() - 2];
		for (int i = 2; i < elements.size(); i++) {
			Element parameterElement = getElement(elements, i, "parameter");
			MyParameter parameter = decodeParameter(parameterElement);
			parameterTypes[i - 2] = classForName(parameter.className);
			parameterValues[i - 2] = parameter.value;
		}
		Method method = proxyClass.getMethod(methodName, parameterTypes);

		Request request = new Request(proxyIdentity.getProxyId(), new Parameter.EqualityParameter(proxyIdentity
				.getProxyStatus()), method, parameterValues);
		return request;
	}

	private Response decodeResponse(Element responseElement) throws Exception {
		String newStatus = responseElement.getAttribute("newstatus");

		List<Element> elements = getElements(responseElement);
		Object returnValue = decodeObject(getElement(elements, 0, "returnvalue"));
		if (elements.size() > 2) {
			throw new IllegalArgumentException("response element has too many children");
		}
		return new Response(newStatus, returnValue);
	}

	/**
	 * Decodes an object from the given DOM representation of the object. The
	 * object is either a return value of a response, or an array component in
	 * such a return value.
	 * 
	 * @param objectElement
	 *            the DOM representation of the object
	 * @return the decoded object
	 * @throws Exception
	 *             if an error occurs
	 */
	private Object decodeObject(Element objectElement) throws Exception {
		List<Element> elements = getElements(objectElement);
		switch (elements.size()) {
		case 0:
			return null;
		case 1:
			Element element = (Element) elements.get(0);
			if (element.getNodeName().equals("array"))
				return decodeArray(element);
			if (element.getNodeName().equals("boolean"))
				return Boolean.valueOf(getText(element));
			if (element.getNodeName().equals("byte"))
				return Byte.valueOf(getText(element));
			if (element.getNodeName().equals("char"))
				return new Character(getText(element).charAt(0));
			if (element.getNodeName().equals("double"))
				return Double.valueOf(getText(element));
			if (element.getNodeName().equals("float"))
				return Float.valueOf(getText(element));
			if (element.getNodeName().equals("int"))
				return Integer.valueOf(getText(element));
			if (element.getNodeName().equals("long"))
				return Long.valueOf(getText(element));
			if (element.getNodeName().equals("short"))
				return Short.valueOf(getText(element));
			if (element.getNodeName().equals("java.lang.String"))
				return getText(element);
			if (element.getNodeName().equals("java.util.Date"))
				return new java.util.Date(Long.parseLong(getText(element)));
			if (element.getNodeName().equals("java.sql.Date"))
				return new java.sql.Date(Long.parseLong(getText(element)));
			if (element.getNodeName().equals("java.sql.Time"))
				return new java.sql.Time(Long.parseLong(getText(element)));
			if (element.getNodeName().equals("java.sql.Timestamp"))
				return new java.sql.Timestamp(Long.parseLong(getText(element)));
			if (element.getNodeName().equals("java.math.BigInteger"))
				return new java.math.BigInteger(getText(element));
			if (element.getNodeName().equals("java.math.BigDecimal"))
				return decodeBigDecimal(element);
			if (element.getNodeName().equals("java.net.URL"))
				return new java.net.URL(getText(element));
			if (element.getNodeName().equals("ProxyObject"))
				return decodeProxyObject(element);
			throw new IllegalArgumentException(objectElement.getNodeName() + " element has unsupported type "
					+ element.getNodeName());
		default:
			throw new IllegalArgumentException(objectElement.getNodeName() + " element has too many children");
		}
	}

	/**
	 * Decodes an array from the given DOM representation of the array.
	 * 
	 * @param arrayElement
	 *            the DOM representation of the array
	 * @return the decoded array
	 * @throws Exception
	 *             if an error occurs
	 */
	private Object decodeArray(Element arrayElement) throws Exception {
		List<Element> elements = getElements(arrayElement);
		Class<?> componentType = classForName(getText(getElement(elements, 0, "componenttype")));
		Object array = Array.newInstance(componentType, elements.size() - 1);
		for (int i = 1; i < elements.size(); i++) {
			Array.set(array, i - 1, decodeObject(getElement(elements, i, "component")));
		}
		return array;
	}

	/**
	 * Decodes a <code>BigDecimal</code> from the given DOM representation of
	 * the big decimal.
	 * 
	 * @param bigDecimalElement
	 *            the DOM representation of the big decimal
	 * @return the decoded big decimal
	 * @throws Exception
	 *             if an error occurs
	 */
	private java.math.BigDecimal decodeBigDecimal(Element bigDecimalElement) {
		List<Element> elements = getElements(bigDecimalElement);
		java.math.BigInteger unscaledValue = new java.math.BigInteger(getText(getElement(elements, 0, "unscaledvalue")));
		int scale = Integer.parseInt(getText(getElement(elements, 1, "scale")));
		if (elements.size() > 2) {
			throw new IllegalArgumentException("java.math.BigDecimal element has too many children");
		}
		return new java.math.BigDecimal(unscaledValue, scale);
	}

	/**
	 * Decodes a {@link ProxyObject} from the given DOM representation of the
	 * proxy object.
	 * 
	 * @param proxyObjectElement
	 *            the DOM representation of the proxy object
	 * @return the decoded proxy object
	 * @throws Exception
	 *             if an error occurs
	 */
	private ProxyObject decodeProxyObject(Element proxyObjectElement) throws Exception {
		String clazzName = proxyObjectElement.getAttribute("class");
		Class<?> proxyClass = classForName(clazzName);
		String id = proxyObjectElement.getAttribute("id");
		String status = proxyObjectElement.getAttribute("status");

		// List<Element> elements = getElements(proxyObjectElement);
		// RequestResponse requestResponseArray[] = new
		// RequestResponse[elements.size()];
		// for (int i = 0; i < elements.size(); i++) {
		// requestResponseArray[i - 3] =
		// decodeRequestResponse(getElement(elements, i, "requestresponse"),
		// proxyClass,
		// id);
		// }
		return new ProxyObject(proxyClass, id, status, null);
	}

	// /**
	// * Decodes a request/response object from the given DOM representation of
	// * the request/response.
	// *
	// * @param requestResponseElement
	// * the DOM representation of the request/response
	// * @param proxyClass
	// * the proxy class of the proxy object for which the
	// * request/response is meant
	// * @param proxyId
	// * the proxy id of the proxy object for which the
	// * request/response is meant
	// * @return the decoded request/response object
	// * @throws Exception
	// * if an error occurs
	// */
	// private RequestResponse decodeRequestResponse(Element
	// requestResponseElement, Class<?> proxyClass, String proxyId)
	// throws Exception {
	// List<Element> elements = getElements(requestResponseElement);
	// Request request = decodeRequest(getElement(elements, 0, "request"),
	// proxyClass, proxyId);
	// Response response = decodeResponse(getElement(elements, 1, "response"));
	// if (elements.size() > 2) {
	// throw new
	// IllegalArgumentException("requestresponse element has too many children");
	// }
	// return new RequestResponse(request, response);
	// }

}
