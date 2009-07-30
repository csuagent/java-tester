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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Encodes {@link Request} objects into their XML representation.
 * 
 * @author Frans van Gool
 */
public class RequestEncoder extends Encoder {
	/**
	 * Encodes the given request object into its XML representation.
	 * 
	 * @param request
	 *            the request to encode
	 * @return a string containing the XML representation of the request
	 * @throws Exception
	 *             if an error occurs
	 */
	public String encode(Request request) throws Exception {
		Method method = request.getDesiredMethod();
		Parameter parameters[] = request.getDesiredParameter();

		Document document = newDocument();
		Element requestElement = document.createElement("request");
		document.appendChild(requestElement);
		requestElement.appendChild(createElement(document, "class", method.getDeclaringClass().getName()));
		requestElement.appendChild(createElement(document, "id", request.getDesiredId()));
		requestElement.appendChild(createParameterElement(document, "status", request.getDesiredStatus()));
		requestElement.appendChild(createElement(document, "method", method.getName()));
		for (int i = 0; i < parameters.length; i++) {
			Element parameterElement = document.createElement("parameter");
			parameterElement.appendChild(createElement(document, "class", method.getParameterTypes()[i].getName()));
			if (parameters[i] != null)
				parameterElement.appendChild(createParameterElement(document, "value", parameters[i]));
			requestElement.appendChild(parameterElement);
		}

		return transformDocument(document);
	}
}
