package org.jtester.jmock;

import org.jmock.Expectations;

public abstract class JTesterExpectations extends Expectations implements ICallMethod {
	public JTesterExpectations() {
		ExpectationsUtil.register(this);
	}

	// public ICallMethod want = this;
	//
	// public IWillReturn will = new IWillReturn(this);

	public JExpections will = new JExpections(this);

	public static class JExpections {

		public JExpections(JTesterExpectations exp) {
			this.call = exp;

			this.returns = new IWillReturn(exp);
		}

		public ICallMethod call;

		public IWillReturn returns;
	}
}
