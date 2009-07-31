package org.jtester.jdbcproxy.stub;

import java.lang.reflect.Method;

import nl.griffelservices.proxy.stub.Encoder;
import nl.griffelservices.proxy.stub.Parameter;
import nl.griffelservices.proxy.stub.Request;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RequestTreeEncoder extends Encoder {
	/**
	 * 拼装request对象为一段xml字符串
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String encode(Request request) throws Exception {
		Method method = request.getDesiredMethod();
		Parameter parameters[] = request.getDesiredParameter();

		Document document = newDocument();
		Element requestElement = document.createElement("request");
		document.appendChild(requestElement);
		requestElement.appendChild(createElement(document, "class", method.getDeclaringClass().getName()));

		requestElement.appendChild(createElement(document, "method", method.getName()));
		for (int i = 0; i < parameters.length; i++) {
			Element parameterElement = document.createElement("parameter");
			parameterElement.setAttribute("class", method.getParameterTypes()[i].getName());
			if (parameters[i] != null) {
				parameterElement.appendChild(createParameterElement(document, "value", parameters[i]));
			}
			requestElement.appendChild(parameterElement);
		}

		String context = transformDocument(document);
		return context.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
	}
}
