package org.jtester.jmock;

import org.jmock.Expectations;

public abstract class JTesterExpectations extends Expectations implements ICallMethod {
	public JTesterExpectations() {
		ExpectationsUtil.register(this);
	}

	protected JTesterExpectations $ = this;

	public ICallMethod call = this;

	public IWillReturn will = new IWillReturn(this);
}
