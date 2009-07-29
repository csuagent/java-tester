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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command-line tool that merges the individual request and response files as written by {@link StubTracerHandler} into a single response.
 *  
 * @author Frans van Gool
 */
public class StubTracerMerger
{
  /** pattern that matches request file names and identifies the id and status in these file names */
  private static final Pattern requestPattern = Pattern.compile("request_(.+)_(.+)\\.txt");
  /** pattern that matches response file names and identifies the id and status in these file names */
  private static final Pattern responsePattern = Pattern.compile("response_(.+)_(.+)\\.txt");
  /** the request decoder */
  private static final RequestDecoder requestDecoder = new RequestDecoder();
  /** the response decoder */
  private static final ResponseDecoder responseDecoder = new ResponseDecoder();
  /** the response encoder */
  private static final ResponseEncoder responseEncoder = new ResponseEncoder();
  
  /** maps {@link ProxyIdentity} objects to {@link RequestResponse} objects */
  private final TreeMap map;
  
  /**
   * Entry point.
   * The first argument is the folder in which the request and response files are located.
   * The second argument is the optional proxy id of the root response (default is 0).
   * The third argument is the optional proxy status of the root response (default is 0).
   * 
   * @param args command line arguments
   * @throws Exception if an error occurs
   */
  public static void main(String args[]) throws Exception
  {
    if (args.length < 1 || args.length > 3)
    {
      System.err.println("Usage: StubTracerMerger <folder> [ <proxyId> [ <proxyStatus> ] ]");
      System.exit(1);
    }
    String folder = args[0];
    String proxyId = args.length >= 2 ? args[1] : "0";
    String proxyStatus = args.length >= 3 ? args[2] : "0";
    StubTracerMerger merger = new StubTracerMerger(new File(folder));
    Response response = merger.getResponse(proxyId, proxyStatus);
    System.out.println(responseEncoder.encode(response));
    System.exit(0);
  }
  
  /**
   * Constructs a StubTracerMerger object.
   * Loads all (decoded) request and response files, but does not merge them yet.
   * 
   * @param folder the folder in which the request and response files are located
   * @throws Exception if an error occurs
   */
  public StubTracerMerger(File folder) throws Exception
  {
    map = new TreeMap();
    File files[] = folder.listFiles();
    for (int i = 0; i < files.length; i++)
    {
      Matcher m;
      
      m = requestPattern.matcher(files[i].getName());
      if (m.matches())
      {
        setRequest(new ProxyIdentity(m.group(1), m.group(2)), requestDecoder.decode(readFile(files[i])));
      }
      
      m = responsePattern.matcher(files[i].getName());
      if (m.matches())
      {
        setResponse(new ProxyIdentity(m.group(1), m.group(2)), responseDecoder.decode(readFile(files[i])));
      }
    }
  }
  
  /**
   * Returns the response that corresponds to the given proxy identity.
   * If the return value of the response  is a {@link ProxyObject}, then all relevant {@link RequestResponse}
   * objects are added to it.
   * A {@link RequestResponse} object is relevant if they are loaded (see {@link #StubTracerMerger(File)}) and
   * the id of the proxy object matches the id of the request.
   * 
   * @param proxyId the id of the proxy object for which the response is needed
   * @param proxyStatus the status of the proxy object for which the response is needed
   * @return the expanded response
   */
  public Response getResponse(String proxyId, String proxyStatus)
  {
    Response response = ((RequestResponse)map.get(new ProxyIdentity(proxyId, proxyStatus))).getResponse();
    return new Response(response.getNewStatus(), expandReturnValue(response.getReturnValue()));
  }
  
  /**
   * Sets the given request for the given proxy identity in the {@link #map}.
   * Any previous request is overwritten.
   * 
   * @param pi the proxy identity
   * @param request the request
   */
  private void setRequest(ProxyIdentity pi, Request request)
  {
    RequestResponse rr = (RequestResponse)map.get(pi);
    if (rr == null)
    {
      map.put(pi, new RequestResponse(request, null));
    }
    else
    {
      map.put(pi, new RequestResponse(request, rr.getResponse()));
    }
  }
  
  /**
   * Sets the given response for the given proxy identity in the {@link #map}.
   * Any previous response is overwritten.
   * 
   * @param pi the proxy identity
   * @param response the response
   */
  private void setResponse(ProxyIdentity pi, Response response)
  {
    RequestResponse rr = (RequestResponse)map.get(pi);
    if (rr == null)
    {
      map.put(pi, new RequestResponse(null, response));
    }
    else
    {
      map.put(pi, new RequestResponse(rr.getRequest(), response));
    }
  }
  
  /**
   * Reads the complete content of a file into a string.
   * 
   * @param file the file to read
   * @return the content of the file
   * @throws Exception if an error occurs
   */
  private static String readFile(File file) throws Exception
  {
    byte bytes[] = new byte[(int)file.length()];
    FileInputStream fis = new FileInputStream(file);
    fis.read(bytes);
    fis.close();
    return new String(bytes);
  }
  
  /**
   * Adds the relevant {@link RequestResponse} objects to the return value if that is a {@link ProxyObject}.
   * A {@link RequestResponse} object is relevant if it can be found in {@link #map} and
   * the id of the proxy object matches the id of the request.
   * 
   * @param returnValue the return value
   * @return the expanded return value
   */
  private Object expandReturnValue(Object returnValue)
  {
    if (returnValue instanceof ProxyObject)
    {
      ProxyObject po = (ProxyObject)returnValue;
      ArrayList requestResponseList = new ArrayList();
      Iterator it = map.values().iterator();
      while (it.hasNext())
      {
        RequestResponse rr = (RequestResponse)it.next();
        if (po.getProxyId().equals(rr.getRequest().getDesiredId()))
        {
          Response response = new Response(rr.getResponse().getNewStatus(), expandReturnValue(rr.getResponse().getReturnValue()));
          requestResponseList.add(new RequestResponse(rr.getRequest(), response));
        }
      }
      RequestResponse requestResponseArray[] = new RequestResponse[requestResponseList.size()];
      for (int i = 0; i < requestResponseArray.length; i++)
      {
        requestResponseArray[i] = (RequestResponse)requestResponseList.get(i);
      }
      return new ProxyObject(po.getProxyClass(), po.getProxyId(), po.getProxyStatus(), requestResponseArray);
    }
    else
    {
      return returnValue;
    }
  }
  
  /**
   * The key of {@link StubTracerMerger#map}.
   * 
   * @author Frans van Gool
   */
  private static class ProxyIdentity implements Comparable
  {
    /** the id of the proxy object for which the {@link RequestResponse} is meant */
    private String proxyId;
    /** the status of the proxy object for which the {@link RequestResponse} is meant */
    private String proxyStatus;
    
    /**
     * Constructs a ProxyIdentity object.
     * 
     * @param proxyId the id of the proxy object for which the {@link RequestResponse} is meant
     * @param proxyStatus the status of the proxy object for which the {@link RequestResponse} is meant
     */
    public ProxyIdentity(String proxyId, String proxyStatus)
    {
      this.proxyId = proxyId;
      this.proxyStatus = proxyStatus;
    }
    
    /**
     * Returns proxyId the id of the proxy object for which the {@link RequestResponse} is meant
     * 
     * @return proxyId the id of the proxy object for which the {@link RequestResponse} is meant
     */
    public String getProxyId()
    {
      return proxyId;
    }
    
    /**
     * Returns the status of the proxy object for which the {@link RequestResponse} is meant
     * 
     * @return the status of the proxy object for which the {@link RequestResponse} is meant
     */
    public String getProxyStatus()
    {
      return proxyStatus;
    }
    
    public boolean equals(Object o)
    {
      if (o instanceof ProxyIdentity)
      {
        ProxyIdentity pi = (ProxyIdentity)o;
        return proxyId.equals(pi.proxyId) && proxyStatus.equals(pi.proxyStatus);
      }
      else
      {
        return false;
      }
    }
    
    public int hashCode()
    {
      return proxyId.hashCode() + proxyStatus.hashCode();
    }

    public int compareTo(Object o)
    {
      ProxyIdentity pi = (ProxyIdentity)o;
      int result = proxyId.length() - pi.proxyId.length();
      if (result == 0)
      {
        result = proxyId.compareTo(pi.proxyId);
      }
      if (result == 0)
      {
        result = proxyStatus.length() - pi.proxyStatus.length();
      }
      if (result == 0)
      {
        result= proxyStatus.compareTo(pi.proxyStatus);
      }
      return result;
    }
  }
}
