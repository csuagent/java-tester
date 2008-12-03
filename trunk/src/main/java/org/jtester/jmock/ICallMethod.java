package org.jtester.jmock;

public interface ICallMethod {
	public <T> T oneOf(T mockObject);

	public <T> T one(T mockObject);
}
