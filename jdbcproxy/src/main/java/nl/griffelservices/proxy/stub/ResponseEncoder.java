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

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Encodes {@link Response} objects into their XML representation.
 * 
 * @author Frans van Gool
 */
public class ResponseEncoder extends Encoder
{
  /**
   * Encodes the given response object into its XML representation.
   *  
   * @param response the response to encode
   * @return a string containing the XML representation of the response
   * @throws Exception if an error occurs
   */
  public String encode(Response response) throws Exception
  {
    Document document = newDocument();
    document.appendChild(encodeResponse(document, response));
    return transformDocument(document);
  }
  
  /**
   * Encodes the given response object into its DOM element representation.
   *  
   * @param document the document for which the element is meant  
   * @param response the response to encode
   * @return the DOM representation of the response
   */
  private Element encodeResponse(Document document, Response response)
  {
    Element responseElement = document.createElement("response");
    responseElement.appendChild(createElement(document, "newstatus", response.getNewStatus()));
    responseElement.appendChild(encodeObject(document, response.getReturnValue(), "returnvalue"));
    return responseElement;
  }
  
  /**
   * Encodes the given object into its DOM element representation.
   * The object is either a return value of a response, or an array component in such a return value.
   * 
   * @param document the document for which the element is meant
   * @param object the object to encode
   * @param elementName the name of the returned element
   * @return the DOM representation of the object
   */
  private Element encodeObject(Document document, Object object, String elementName)
  { 
    Element objectElement = document.createElement(elementName);
    Class objectType = object == null ? null : object.getClass();
    if (objectType == null)
    {
      // do nothing
    }
    else if (objectType.isArray())
    {
      Element arrayElement = document.createElement("array");
      arrayElement.appendChild(createElement(document, "componenttype", objectType.getComponentType().getName()));
      int length = Array.getLength(object);
      for (int i = 0; i < length; i++)
      {
        arrayElement.appendChild(encodeObject(document, Array.get(object, i), "component"));
      }
      objectElement.appendChild(arrayElement);
    }
    else if (objectType.equals(boolean.class) || objectType.equals(Boolean.class))
    {
      objectElement.appendChild(createElement(document, "boolean", object.toString()));
    }
    else if (objectType.equals(byte.class) || objectType.equals(Byte.class))
    {
      objectElement.appendChild(createElement(document, "byte", object.toString()));
    }
    else if (objectType.equals(char.class) || objectType.equals(Character.class))
    {
      objectElement.appendChild(createElement(document, "char", object.toString()));
    }
    else if (objectType.equals(double.class) || objectType.equals(Double.class))
    {
      objectElement.appendChild(createElement(document, "double", object.toString()));
    }
    else if (objectType.equals(float.class) || objectType.equals(Float.class))
    {
      objectElement.appendChild(createElement(document, "float", object.toString()));
    }
    else if (objectType.equals(int.class) || objectType.equals(Integer.class))
    {
      objectElement.appendChild(createElement(document, "int", object.toString()));
    }
    else if (objectType.equals(long.class) || objectType.equals(Long.class))
    {
      objectElement.appendChild(createElement(document, "long", object.toString()));
    }
    else if (objectType.equals(short.class) || objectType.equals(Short.class))
    {
      objectElement.appendChild(createElement(document, "short", object.toString()));
    }
    else if (objectType.equals(String.class))
    {
      objectElement.appendChild(createElement(document, "java.lang.String", object.toString()));
    }
    else if (objectType.equals(java.util.Date.class))
    {
      objectElement.appendChild(createElement(document, "java.util.Date", String.valueOf(((java.util.Date)object).getTime())));
    }
    else if (objectType.equals(java.sql.Date.class))
    {
      objectElement.appendChild(createElement(document, "java.sql.Date", String.valueOf(((java.sql.Date)object).getTime())));
    }
    else if (objectType.equals(java.sql.Time.class))
    {
      objectElement.appendChild(createElement(document, "java.sql.Time", String.valueOf(((java.sql.Time)object).getTime())));
    }
    else if (objectType.equals(java.sql.Timestamp.class))
    {
      objectElement.appendChild(createElement(document, "java.sql.Timestamp", String.valueOf(((java.sql.Timestamp)object).getTime())));
    }
    else if (objectType.equals(java.math.BigInteger.class))
    {
      objectElement.appendChild(createElement(document, "java.math.BigInteger", object.toString()));
    }
    else if (objectType.equals(java.math.BigDecimal.class))
    {
      java.math.BigDecimal v = (java.math.BigDecimal)object;
      Element bigDecimalElement = document.createElement("java.math.BigDecimal");
      bigDecimalElement.appendChild(createElement(document, "unscaledvalue", v.unscaledValue().toString()));
      bigDecimalElement.appendChild(createElement(document, "scale", String.valueOf(v.scale())));
      objectElement.appendChild(bigDecimalElement);
    }
    else if (objectType.equals(java.net.URL.class))
    {
      objectElement.appendChild(createElement(document, "java.net.URL", object.toString()));
    }
    else if (objectType.equals(ProxyObject.class))
    {
      ProxyObject proxyObject = (ProxyObject)object;
      Element proxyObjectElement = document.createElement("nl.griffelservices.proxy.stub.ProxyObject");
      proxyObjectElement.appendChild(createElement(document, "class", proxyObject.getProxyClass().getName()));
      proxyObjectElement.appendChild(createElement(document, "id", proxyObject.getProxyId()));
      proxyObjectElement.appendChild(createElement(document, "status", proxyObject.getProxyStatus()));
      for (int i = 0; i < proxyObject.getRequestResponseCount(); i++)
      {
        Element requestResponseElement = document.createElement("requestresponse");
        requestResponseElement.appendChild(encodeRequest(document, proxyObject.getRequestResponse(i).getRequest()));
        requestResponseElement.appendChild(encodeResponse(document, proxyObject.getRequestResponse(i).getResponse()));
        proxyObjectElement.appendChild(requestResponseElement);
      }
      objectElement.appendChild(proxyObjectElement);
    }
    else
    {
      throw new IllegalArgumentException(elementName + " element has unsupported type " + object.getClass());
    }
      
    return objectElement;
  }
  
  /**
   * Encodes the given request object into its DOM element representation.
   * Note that the encoding done here is not the same as in {@link RequestEncoder#encode(Request)}.
   * Since the encoding done here is intended to be enclosed in the encoding of a {@link ProxyObject},
   * there is no need to encode the class and id of the request.
   *  
   * @param document the document for which the element is meant
   * @param request the request to encode
   * @return the DOM representation of the request
   */
  private Element encodeRequest(Document document, Request request)
  {
    Method method = request.getDesiredMethod();
    Request.Parameter parameters[] = request.getDesiredParameter();

    Element requestElement = document.createElement("request");
    document.appendChild(requestElement);
    requestElement.appendChild(createParameterElement(document, "status", request.getDesiredStatus()));
    requestElement.appendChild(createElement(document, "method", method.getName()));
    for (int i = 0; i < parameters.length; i++)
    {
      Element parameterElement = document.createElement("parameter");
      parameterElement.appendChild(createElement(document, "class", method.getParameterTypes()[i].getName()));
      if (parameters[i] != null) parameterElement.appendChild(createParameterElement(document, "value", parameters[i]));
      requestElement.appendChild(parameterElement);
    }
    return requestElement;
  }
}
