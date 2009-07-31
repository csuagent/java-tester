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

/**
 * This is the proxy data used by the {@link StubHandler}.
 * 
 * @author Frans van Gool
 * @see org.jtester.jdbcproxy.proxy.Proxy
 */
public class ProxyObject implements Cloneable {
	/** the interface for which this is the proxy data */
	private Class<?> proxyClass;
	/** the id of the object for which this is the proxy data */
	private String proxyId;
	/** the status of the object for which this is the proxy data */
	private String proxyStatus;
	/** the pre-configured stub responses for a set of requests */
	private RequestResponse requestResponse[];

	/**
	 * Constructs a ProxyObject object.
	 * 
	 * @param proxyClass
	 *            the interface for which this is the proxy data
	 * @param proxyId
	 *            the id of the object for which this is the proxy data
	 * @param proxyStatus
	 *            the status of the object for which this is the proxy data
	 * @param requestResponse
	 *            the pre-configured stub responses for a set of requests
	 */
	public ProxyObject(Class<?> proxyClass, String proxyId, String proxyStatus, RequestResponse requestResponse[]) {
		this.proxyClass = proxyClass;
		this.proxyId = proxyId;
		this.proxyStatus = proxyStatus;
		this.requestResponse = requestResponse;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("proxy object{");
		buffer.append("class=" + proxyClass.getName() + ",");
		buffer.append("proxy id=" + proxyId + ",");
		buffer.append("proxy status=" + this.proxyStatus + ",");

		buffer.append("request response=" + this.requestResponse + "}");
		return buffer.toString();
	}

	/**
	 * Returns the interface for which this is the proxy data
	 * 
	 * @return the interface for which this is the proxy data
	 */
	public Class<?> getProxyClass() {
		return proxyClass;
	}

	/**
	 * Returns the id of the object for which this is the proxy data
	 * 
	 * @return the id of the object for which this is the proxy data
	 */
	public String getProxyId() {
		return proxyId;
	}

	/**
	 * Returns the status of the object for which this is the proxy data
	 * 
	 * @return the status of the object for which this is the proxy data
	 */
	public String getProxyStatus() {
		return proxyStatus;
	}

	/**
	 * Sets the new status of the object for which this is the proxy data
	 * 
	 * @param proxyStatus
	 *            the new status of the object for which this is the proxy data
	 */
	public void setProxyStatus(String proxyStatus) {
		this.proxyStatus = proxyStatus;
	}

	/**
	 * Returns the number of pre-configured request/response pairs
	 * 
	 * @return the number of pre-configured request/response pairs
	 */
	public int getRequestResponseCount() {
		return requestResponse == null ? 0 : requestResponse.length;
	}

	/**
	 * Returns the pre-configured request/response pair with the given index
	 * 
	 * @param i
	 *            the index of the desired pre-configured request/response pair
	 * @return the desired pre-configured request/response pair
	 */
	public RequestResponse getRequestResponse(int i) {
		return requestResponse[i];
	}

	public RequestResponse[] getRequestResponses() {
		return requestResponse == null ? new RequestResponse[0] : requestResponse;
	}

	public Object clone() {
		return new ProxyObject(proxyClass, proxyId, proxyStatus, requestResponse);
	}
}
