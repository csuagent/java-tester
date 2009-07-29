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

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Retrieves data from a HTTP server using POST requests.
 * 
 * @author Frans van Gool
 */
public class HttpClient
{
  /** the default encoding for the HTTP content */
  private static final String DEFAULT_ENCODING = "ISO-8859-1";
  
  /** the hostname of the HTTP server for which this is the client */
  private String host;
  /** the port of the HTTP server for which this is the client */
  private int port;
  /** the timeout that will be used for HTTP requests to the HTTP server */
  private int timeout;
  
  /**
   * Constructs a HttpClient object.
   * 
   * @param host the hostname of the HTTP server for which this is the client
   * @param port the port of the HTTP server for which this is the client
   * @param timeout the timeout that will be used for HTTP requests to the HTTP server
   */
  public HttpClient(String host, int port, int timeout)
  {
    this.host = host;
    this.port = port;
    this.timeout = timeout;
  }
  
  /**
   * Sends a string as the content of a HTTP Post request to the server and returns the content of the HTTP response.
   * 
   * @param request the request that must be send to the HTTP server
   * @return the response from the HTTP server
   * @throws IOException if an error occurs
   */
  public String invoke(String request) throws IOException
  {
    //System.out.println(request);
    Socket socket = new Socket(host, port);
    socket.setSoTimeout(timeout);

    byte requestContent[] = request.getBytes(DEFAULT_ENCODING);
    StringBuffer requestHeader = new StringBuffer();
    requestHeader.append("POST / HTTP/1.1\r\n");
    requestHeader.append("Host: " + host + ":" + port + "\r\n");
    requestHeader.append("Content-Type: text/plain\r\n");
    requestHeader.append("Content-Length: ").append(requestContent.length).append("\r\n");
    requestHeader.append("\r\n");
    OutputStream outputStream = socket.getOutputStream();
    outputStream.write(requestHeader.toString().getBytes());
    outputStream.write(requestContent);
    outputStream.flush();
    
    InputStream inputStream = socket.getInputStream();
    StringBuffer responseHeader = new StringBuffer();
    int i;
    while ((i = inputStream.read()) != -1)
    {
      char c = (char)i;
      responseHeader.append(c);
      if (responseHeader.toString().endsWith("\r\n\r\n"))
      {
        break;
      }
    }
    if (!responseHeader.toString().endsWith("\r\n\r\n"))
    {
      throw new EOFException("Cannot read header");
    }
    String responseHeaderLines[] = responseHeader.toString().split("\r\n");
    int responseContentLength = Integer.parseInt(getHeaderField(responseHeaderLines, "Content-Length", null));
    byte responseContent[] = new byte[responseContentLength];
    for (int offset = 0; offset < responseContentLength;)
    {
      int result = inputStream.read(responseContent, offset, responseContentLength - offset);
      if (result < 0)
      {
        throw new EOFException("cannot read content");
      }
      offset += result;
    }
    inputStream.close();
    outputStream.close();
    socket.close();
    String response = new String(responseContent, getParameter(getHeaderField(responseHeaderLines, "Content-Type", ""), "charset", DEFAULT_ENCODING));
    //System.out.println(response);
    return response;
  }
  
  /**
   * Gets the value of the desired header field from the given header lines.
   *  
   * @param headerLines the header lines
   * @param fieldName the name of the header field
   * @param defaultValue the value that must be returned if the header field is not found
   * @return the value of the header field
   */
  private static String getHeaderField(String headerLines[], String fieldName, String defaultValue)
  {
    String lowerCaseFieldName = fieldName.toLowerCase();
    for (int i = 1; i < headerLines.length; i++)
    {
      String headerLine = headerLines[i].trim();
      if (headerLine.toLowerCase().startsWith(lowerCaseFieldName))
      {
        headerLine = headerLine.substring(fieldName.length()).trim();
        if (headerLine.charAt(0) == ':')
        {
          return headerLine.substring(1).trim();
        }
      }
    }
    return defaultValue;
  }
  
  /**
   * Gets the value of the desired parameter from the given HTTP header field.
   * The structure of the header field value is suppoed to be as follows:
   * <code>fieldvalue [; paramname = paramvalue]*</code>
   * 
   * @param headerField the value of the header field
   * @param parameterName the name of the parameter
   * @param defaultValue the value that must be returned if the parameter is not found
   * @return the value of the parameter
   */
  private static String getParameter(String headerField, String parameterName, String defaultValue)
  {
    String lowerCaseParameterName = parameterName.toLowerCase();
    String parameters[] = headerField.split(";");
    for (int i = 1; i < parameters.length; i++)
    {
      String parameter = parameters[i].trim();
      if (parameter.toLowerCase().startsWith(lowerCaseParameterName))
      {
        parameter = parameter.substring(parameterName.length()).trim();
        if (parameter.charAt(0) == '=')
        {
          return parameter.substring(1).trim();
        }
      }
    }
    return defaultValue;
  }
}
