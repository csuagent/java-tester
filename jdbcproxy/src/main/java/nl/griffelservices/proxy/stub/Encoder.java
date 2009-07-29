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
 * GNU Lesser General Public License (the “LGPL License”), in which case the
 * provisions of LGPL License are applicable instead of those above. If you wish
 * to allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under the MPL,
 * indicate your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do not
 * delete the provisions above, a recipient may use your version of this file
 * under either the MPL or the LGPL License.
 */
package nl.griffelservices.proxy.stub;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Base class for other encoders that implements some utility functions.
 * 
 * @author Frans van Gool
 */
public class Encoder
{
  /** factory that creates the <code>DocumentBuilder</code> objects used for encoding */
  private DocumentBuilderFactory documentBuilderFactory;
  /** factory that creates the <code>Transformer</code> objects used for transforming DOM objects to a string */
  private TransformerFactory transformerFactory;

  /**
   * Constructs a new Encoder object.
   */
  public Encoder()
  {
    documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);  
    transformerFactory = TransformerFactory.newInstance();
  }
  
  /**
   * Constructs a new empty Document object.
   * 
   * @return a new empty Document object
   * @throws ParserConfigurationException if an error occurs
   */
  protected Document newDocument() throws ParserConfigurationException
  {
    DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
    return builder.newDocument();
  }
  
  /**
   * Transforms a Document object into a string containing an XML document.
   * 
   * @param document the Document object to transform
   * @return a string containing an XML representation of the given Document object
   * @throws TransformerException if an error occurs
   */
  protected String transformDocument(Document document) throws TransformerException
  {
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
    StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(writer));
    return writer.toString();
  }
  
  /**
   * Constructs an Element object with a given name and a given text content.
   * 
   * @param document the document for which the element is meant
   * @param name the desired name of the element
   * @param text the desired text content of the element
   * @return an Element object with a given name and a given text content
   */
  protected Element createElement(Document document, String name, String text)
  {
    Element element = document.createElement(name);
    element.appendChild(document.createTextNode(text));
    return element;
  }

  
  /**
   * Constructs an Element object with a given name and a given parameter content.
   * 
   * @param document the document for which the element is meant
   * @param name the desired name of the element
   * @param parameter the desired parameter content of the element
   * @return an Element object with a given name and a given text content
   */
  protected Element createParameterElement(Document document, String name, Request.Parameter parameter)
  {
    Element element = document.createElement(name);
    if (parameter instanceof Request.EqualityParameter)
    {
      // do nothing
    }
    else if (parameter instanceof Request.RegexParameter)
    {
      element.setAttribute("type", "regex");
    }
    else
    {
      throw new IllegalArgumentException("Illegal parameter type: " + parameter.getClass().getName());
    }
    element.appendChild(document.createTextNode(parameter.getDesiredValue()));
    return element;
  }
}
