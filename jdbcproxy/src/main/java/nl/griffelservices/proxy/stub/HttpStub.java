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

/**
 * This stub gets the responses from an HTTP server.
 * 
 * @author Frans van Gool
 */
public class HttpStub extends HttpClient implements Stub
{
  /** the request encoder */
  private static final RequestEncoder encoder = new RequestEncoder();
  /** the response decoder */
  private static final ResponseDecoder decoder = new ResponseDecoder();
  
  /**
   * Constructs a HttpStub object.
   * 
   * @param host the hostname of the HTTP server that provides the stub information
   * @param port the port of the HTTP server that provides the stub information
   * @param timeout the timeout that will be used for HTTP requests to the HTTP server that provides the stub information
   */
  public HttpStub(String host, int port, int timeout)
  {
    super(host, port, timeout);
  }

  public Response invoke(Request request) throws Exception
  {
    return decoder.decode(invoke(encoder.encode(request)));
  }
}
