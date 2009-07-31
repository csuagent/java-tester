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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

/**
 * Base class for other decoders that implements some utility functions.
 * 
 * @author Frans van Gool
 */
public class Decoder {
	/**
	 * factory that creates the <code>DocumentBuilder</code> objects used for
	 * decoding
	 */
	private DocumentBuilderFactory factory;

	/**
	 * Constructs a new Decoder object.
	 */
	public Decoder() {
		factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
	}

	/**
	 * Parses a string containing an XML document into a <code>Document</code>
	 * object.
	 * 
	 * @param document
	 *            the string containing an XML document
	 * @return the DOM object representing the XML document
	 * @throws Exception
	 *             if an error occurs
	 */
	protected Document parse(String document) throws Exception {
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new InputSource(new StringReader(document)));
	}

	/**
	 * Returns all the child element nodes from the given node. All other child
	 * nodes from teh given node are ignored.
	 * 
	 * @param node
	 *            the nodes whose child elements are needed
	 * @return all the child element nodes from the given node
	 */
	protected List<Element> getElements(Node node) {
		List<Element> elements = new ArrayList<Element>();
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				elements.add((Element) nodes.item(i));
			}
		}
		return elements;
	}

	/**
	 * Returns an element from the given list of elements if it exists at the
	 * given index and has the given name.
	 * 
	 * @param elements
	 *            the list of elements
	 * @param index
	 *            the index of the desired element
	 * @param name
	 *            the name of the desired element
	 * @return the desired element
	 * @throws IllegalArgumentException
	 *             if there is no element at the desired index of if such an
	 *             element has a different name
	 */
	protected Element getElement(List<Element> elements, int index, String name) {
		if (elements.size() <= index || !((Element) elements.get(index)).getNodeName().equals(name)) {
			throw new IllegalArgumentException(name + " element expected");
		}
		return (Element) elements.get(index);
	}

	/**
	 * Returns the text content of the given element.
	 * 
	 * @param element
	 *            the element whose text content is needed
	 * @return the text content of the given element
	 * @throws IllegalArgumentException
	 *             if the element has multiple children.
	 * @throws ClassCastException
	 *             if the single child of the element is not a <code>Text</code>
	 *             node.
	 */
	protected String getText(Element element) {
		NodeList nodes = element.getChildNodes();
		switch (nodes.getLength()) {
		case 0:
			return "";
		case 1:
			String text = ((Text) nodes.item(0)).getNodeValue();
			return text == null ? null : text.trim();
		default:
			throw new IllegalArgumentException("element " + element.getNodeName() + " contains more than just text");
		}
	}

	/**
	 * Returns the parameter content of the given element.
	 * 
	 * @param element
	 *            the element whose text content is needed
	 * @return the parameter content of the given element
	 * @throws IllegalArgumentException
	 *             if the element has multiple children.
	 * @throws ClassCastException
	 *             if the single child of the element is not a <code>Text</code>
	 *             node.
	 */
	protected Parameter getParameter(Element element) {
		String value = getText(element);
		Attr typeNode = element.getAttributeNode("type");
		String type = typeNode == null ? null : typeNode.getNodeValue();
		if (type == null || type.equals("equality")) {
			return new Parameter.EqualityParameter(value);
		} else if (type.equals("regex")) {
			return new Parameter.RegexParameter(value);
		} else {
			throw new IllegalArgumentException("element " + element.getNodeName() + " has unsupported type: " + type);
		}
	}

	/**
	 * Wrapper around <code>Class.forName(String)</code> that can also handle
	 * primitive types.
	 * 
	 * @param name
	 *            the name of the type
	 * @return the <code>Class</code> object of the type
	 * @throws ClassNotFoundException
	 *             if the class cannot be located
	 */
	protected Class<?> classForName(String name) throws ClassNotFoundException {
		if (name.equals("boolean"))
			return boolean.class;
		if (name.equals("byte"))
			return byte.class;
		if (name.equals("char"))
			return char.class;
		if (name.equals("double"))
			return double.class;
		if (name.equals("float"))
			return float.class;
		if (name.equals("int"))
			return int.class;
		if (name.equals("long"))
			return long.class;
		if (name.equals("short"))
			return short.class;
		return Class.forName(name);
	}

	/**
	 * Decodes a type/value pair from the given DOM representation of the
	 * request parameter.
	 * 
	 * @param parameter
	 *            the DOM representation of the request parameter
	 * @return a string array with two components: the first is the type and the
	 *         second is the value of the parameter
	 */
	protected MyParameter decodeParameter(Element parameter) {
		List<Element> elements = getElements(parameter);
		MyParameter decodedParameter = new MyParameter();
		decodedParameter.className = parameter.getAttribute("class");
		if (elements.size() == 0) {
			decodedParameter.value = null;
		} else {
			decodedParameter.value = getParameter(getElement(elements, 0, "value"));
		}
		if (elements.size() > 1) {
			throw new IllegalArgumentException("parameter element has too many children");
		}
		return decodedParameter;
	}

	/**
	 * Represents a decoded parameter class/value pair.
	 * 
	 * @author Frans van Gool
	 */
	protected static class MyParameter {
		/** the parameter class */
		public String className;
		/** the parameter value */
		public Parameter value;
	}
}
