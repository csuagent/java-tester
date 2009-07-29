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
package nl.griffelservices.proxy;

import java.lang.reflect.Method;

/**
 * The handler is the actual implementation of the proxy classes.
 * The proxy classes themselves just pass the interface method calls to the handler.
 * This makes it possible for a single handler class to implement many different interfaces in a consistent way.
 * Handlers can pass the calls to another implementation of the interfaces;
 * adding extra functionality like tracing or access control.
 * Handlers can also provide an implementation of the interfaces that is completely different from other implementations.
 *  
 * @author Frans van Gool
 */
public interface Handler
{
  /**
   * Called when a proxy class is constructed.
   * This is just a notification to the handler;
   * the handler is allowed to ignore this call. 
   * 
   * @param proxyClass the proxy class
   * @param proxyObject the data passed to the proxy class constructor
   */
  public void init(Class proxyClass, Object proxyObject);
  
  /**
   * Called when an interface method of a proxy class is called.
   * This is the most crucial method of a handler;
   * it must return the value that is expected from the interface method call.
   * 
   * @param proxy the proxy class whose method is called
   * @param method the method that is called
   * @param parameters the parameters of the method that is called
   * @return the return value of the called method
   * @throws Exception if an error occurs
   */
  public Object invoke(Proxy proxy, Method method, Object parameters[]) throws Exception;
}
