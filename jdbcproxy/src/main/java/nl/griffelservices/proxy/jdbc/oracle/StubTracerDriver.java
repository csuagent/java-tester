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
package nl.griffelservices.proxy.jdbc.oracle;

import java.sql.DriverManager;

import nl.griffelservices.proxy.Handler;
import nl.griffelservices.proxy.jdbc.util.CombinedHandler;

/**
 * This class implements a JDBC 2.0 driver proxy using the {@link CombinedHandler}.
 * Note that this class is not located in the <code>nl.griffelservices.proxy.jdbc.util</code> package.
 * The main reason for this is that TIBCO BusinessWorks (for which this driver was originally planned)
 * handles drivers with <code>oracle</code> in their package different from other drivers.
 * 
 * @author Frans van Gool
 */
public class StubTracerDriver extends nl.griffelservices.proxy.jdbc.DriverProxy
{
  static
  {
    try
    {
      DriverManager.registerDriver(new StubTracerDriver());
    }
    catch (RuntimeException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructs a new StubTracerDriver object.
   */
  public StubTracerDriver()
  {
    this(new CombinedHandler(1, 0, true), null);
  }

  /**
   * Constructs a new StubTracerDriver object.
   * 
   * @param handler the proxy handler
   * @param proxyObject the proxy data
   */
  public StubTracerDriver(Handler handler, Object proxyObject)
  {
    super(handler, proxyObject);
  }
}
