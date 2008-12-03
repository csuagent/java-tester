package org.jtester.jmock;

import org.jmock.Expectations;

public class JTesterExpectations extends Expectations implements ICallMethod {
	protected JTesterExpectations $ = this;

	public ICallMethod call = this;

	public IWillReturn will = new IWillReturn(this);
}
