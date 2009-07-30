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
import java.util.List;

import org.w3c.dom.Element;

/**
 * Decodes {@link Request} objects from their XML representation.
 * 
 * @author Frans van Gool
 */
public class RequestDecoder extends Decoder {
	/**
	 * Decodes a request object from the given string containing an XML
	 * representation of the request.
	 * 
	 * @param request
	 *            the XML representation of the request
	 * @return the decoded request object
	 * @throws Exception
	 *             if an error occurs
	 */
	public Request decode(String request) throws Exception {
		Element root = parse(request).getDocumentElement();
		if (!root.getNodeName().equals("request")) {
			throw new IllegalArgumentException("request element expected");
		}
		return decodeRequest(root);
	}

	/**
	 * Decodes a request object from the given DOM representation of the
	 * request.
	 * 
	 * @param requestElement
	 *            the DOM representation of the request
	 * @return the decoded request object
	 * @throws Exception
	 *             if an error occurs
	 */
	private Request decodeRequest(Element requestElement) throws Exception {
		List<Element> elements = getElements(requestElement);
		String className = getText(getElement(elements, 0, "class"));
		String id = getText(getElement(elements, 1, "id"));
		Request.Parameter status = getParameter(getElement(elements, 2, "status"));
		String methodName = getText(getElement(elements, 3, "method"));
		Class<?> parameterTypes[] = new Class[elements.size() - 4];
		Request.Parameter parameterValues[] = new Request.Parameter[elements.size() - 4];
		for (int i = 4; i < elements.size(); i++) {
			Parameter parameter = decodeParameter(getElement(elements, i, "parameter"));
			parameterTypes[i - 4] = classForName(parameter.className);
			parameterValues[i - 4] = parameter.value;
		}
		Method method = Class.forName(className).getMethod(methodName, parameterTypes);
		return new Request(id, status, method, parameterValues);
	}
}
