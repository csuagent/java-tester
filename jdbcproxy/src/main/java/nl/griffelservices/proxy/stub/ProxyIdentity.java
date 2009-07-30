package nl.griffelservices.proxy.stub;

/**
 * The key of {@link StubTracerMerger#map}.
 * 
 * @author Frans van Gool
 */
public class ProxyIdentity implements Comparable<ProxyIdentity> {
	/**
	 * the id of the proxy object for which the {@link RequestResponse} is meant
	 */
	private String proxyId;
	/**
	 * the status of the proxy object for which the {@link RequestResponse} is
	 * meant
	 */
	private String proxyStatus;

	/**
	 * Constructs a ProxyIdentity object.
	 * 
	 * @param proxyId
	 *            the id of the proxy object for which the
	 *            {@link RequestResponse} is meant
	 * @param proxyStatus
	 *            the status of the proxy object for which the
	 *            {@link RequestResponse} is meant
	 */
	public ProxyIdentity(String proxyId, String proxyStatus) {
		this.proxyId = proxyId;
		this.proxyStatus = proxyStatus;
	}

	/**
	 * Returns proxyId the id of the proxy object for which the
	 * {@link RequestResponse} is meant
	 * 
	 * @return proxyId the id of the proxy object for which the
	 *         {@link RequestResponse} is meant
	 */
	public String getProxyId() {
		return proxyId;
	}

	/**
	 * Returns the status of the proxy object for which the
	 * {@link RequestResponse} is meant
	 * 
	 * @return the status of the proxy object for which the
	 *         {@link RequestResponse} is meant
	 */
	public String getProxyStatus() {
		return proxyStatus;
	}

	public boolean equals(Object o) {
		if (o instanceof ProxyIdentity) {
			ProxyIdentity pi = (ProxyIdentity) o;
			return proxyId.equals(pi.proxyId) && proxyStatus.equals(pi.proxyStatus);
		} else {
			return false;
		}
	}

	public int hashCode() {
		return proxyId.hashCode() + proxyStatus.hashCode();
	}

	public int compareTo(ProxyIdentity pi) {
		// ProxyIdentity pi = (ProxyIdentity) o;
		int result = proxyId.length() - pi.proxyId.length();
		if (result == 0) {
			result = proxyId.compareTo(pi.proxyId);
		}
		if (result == 0) {
			result = proxyStatus.length() - pi.proxyStatus.length();
		}
		if (result == 0) {
			result = proxyStatus.compareTo(pi.proxyStatus);
		}
		return result;
	}
}
