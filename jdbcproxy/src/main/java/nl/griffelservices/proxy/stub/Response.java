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
 * This class represents a response to specific call to a method of a proxy class in a particular state.
 * It specifies both the return value of the call and the new state of the proxy class.
 * 
 * This class also has an XML representation.
 * {@link ResponseEncoder} encodes objects of this class into this XML representation.
 * {@link ResponseDecoder} decodes this XML representation into objects of this class.
 * 
 * @author Frans van Gool
 */
public class Response
{
  /** the status of the proxy class after the method call */
  private final String newStatus;
  /** the return value of the method call */
  private final Object returnValue;
  
  /**
   * Constructs a new Response object.
   * 
   * @param newStatus the status of the proxy class after the method call
   * @param returnValue the return value of the method call
   */
  public Response(String newStatus, Object returnValue)
  {
    this.newStatus = newStatus;
    this.returnValue = returnValue;
  }
  
  /**
   * Returns the status of the proxy class after the method call
   * 
   * @return the status of the proxy class after the method call
   */
  public String getNewStatus()
  {
    return newStatus;
  }
  
  /**
   * Returns the return value of the method call
   * 
   * @return the return value of the method call
   */
  public Object getReturnValue()
  {
    return returnValue;
  }

  public String toString()
  {
    return Response.class.getName() + "[" + newStatus + "," + returnValue + "]";
  }
}
