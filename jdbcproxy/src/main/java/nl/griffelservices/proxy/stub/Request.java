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

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * This class represents a specific call to a method of a proxy class in a particular state.
 * 
 * This class also has an XML representation.
 * {@link RequestEncoder} encodes objects of this class into this XML representation.
 * {@link RequestDecoder} decodes this XML representation into objects of this class.
 * 
 * @author Frans van Gool
 */
public class Request
{
  /** the id of the proxy class for which the method is called */
  private final String desiredId;
  /** the status of the proxy class when the method is called */
  private final Parameter desiredStatus;
  /** the method that is called */
  private final Method desiredMethod;
  /** the parameters of the method that is called */
  private final Parameter desiredParameter[];
  
  /**
   * Constructs a Request object.
   * 
   * @param desiredId the id of the proxy class for which the method is called
   * @param desiredStatus the status of the proxy class when the method is called
   * @param desiredMethod the method that is called
   * @param desiredParameter the parameters of the method that is called
   */
  public Request(String desiredId, Parameter desiredStatus, Method desiredMethod, Parameter desiredParameter[])
  {
    if (desiredMethod.getParameterTypes().length != desiredParameter.length)
    {
      throw new IllegalArgumentException("Number of parameters " + desiredParameter.length + " does not match method " + desiredMethod);
    }
    this.desiredId = desiredId;
    this.desiredStatus = desiredStatus;
    this.desiredMethod = desiredMethod;
    this.desiredParameter = desiredParameter;
  }
  
  /**
   * Validates that the desired id, status, method and parameters as specified when this object was constructed
   * match the actual id, status, method and parameters that are passed to this method.
   * 
   * @param actualId the id of the proxy class for which the method is called
   * @param actualStatus the status of the proxy class when the method is called
   * @param actualMethod the method that is called
   * @param actualParameter the parameters of the method that is called
   * @return whether the status, method and parameters are the same
   */
  public boolean matches(String actualId, String actualStatus, Method actualMethod, Object actualParameter[])
  {
    if (!desiredId.equals(actualId) || !desiredStatus.matches(actualStatus))
    {
      return false;
    }
    if (desiredMethod.equals(actualMethod) && desiredParameter.length == actualParameter.length)
    {
      for (int i = 0; i < desiredParameter.length; i++)
      {
        if (desiredParameter[i] == null)
        {
          return actualParameter[i] != null;
        }
        else if (!desiredParameter[i].matches(actualParameter[i]))
        {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Returns the id of the proxy class for which the method is called
   * 
   * @return the id of the proxy class for which the method is called
   */
  public String getDesiredId()
  {
    return desiredId;
  }

  /**
   * Returns the status of the proxy class when the method is called
   * 
   * @return the status of the proxy class when the method is called
   */
  public Parameter getDesiredStatus()
  {
    return desiredStatus;
  }
  
  /**
   * Returns the method that is called
   * 
   * @return the method that is called
   */
  public Method getDesiredMethod()
  {
    return desiredMethod;
  }

  /**
   * Returns the parameters of the method that is called
   * 
   * @return the parameters of the method that is called
   */
  public Parameter[] getDesiredParameter()
  {
    return desiredParameter;
  }
  
  /**
   * Abstract class for validating request parameters.
   * 
   * @author Frans van Gool
   */
  public static abstract class Parameter
  {
    /** the desired value of the parameter */
    private String desiredValue;
    
    /**
     * Constructs a StringParameter object.
     * 
     * @param desiredValue the desired value of the parameter
     */
    public Parameter(String desiredValue)
    {
      this.desiredValue = desiredValue;
    }
    
    /**
     * Returns the desired value of the parameter
     * 
     * @return the desired value of the parameter
     */
    public String getDesiredValue()
    {
      return desiredValue;
    }
    
    /**
     * Tests whether the desired and actual values match.
     * 
     * @param actualValue the actual value
     * @return whether the desired and actual values match.
     */
    public abstract boolean matches(Object actualValue);
  }
  
  /**
   * In this parameter implementation, the actual and desired values match if
   * the string representation of the actual value is equal to the desired value.
   *  
   * @author Frans van Gool
   */
  public static class EqualityParameter extends Parameter 
  {
    /**
     * Constructs a EqualityParameter object.
     * 
     * @param desiredValue the desired value of the parameter
     */
    public EqualityParameter(String desiredValue)
    {
      super(desiredValue);
    }
    
    public boolean matches(Object actualValue)
    {
      return actualValue != null && getDesiredValue().equals(actualValue.toString());
    }
  }
  
  /**
   * In this parameter implementation, the actual and desired values match if 
   * the string representation of the actualvalue matches the desired value regular expression.
   *  
   * @author Frans van Gool
   */
  public static class RegexParameter extends Parameter 
  {
    /**
     * Constructs a RegexParameter object.
     * 
     * @param desiredValue the desired value of the parameter
     */
    public RegexParameter(String desiredValue)
    {
      super(desiredValue);
    }
    
    public boolean matches(Object actualValue)
    {
      return actualValue != null && Pattern.matches(getDesiredValue(), actualValue.toString());
    }
  }
}
