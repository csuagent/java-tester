package org.jtester.jmock;

import org.jmock.Expectations;
import org.jmock.api.Action;

public abstract class JTesterExpectations extends Expectations implements ICallMethod {
	public JTesterExpectations() {
		ExpectationsUtil.register(this);
	}

	public JExpections will = new JExpections(this);

	public static class JExpections {

		public JExpections(JTesterExpectations expectations) {
			this.expectations = expectations;
			this.call = expectations;
			this.returns = new IWillReturn(expectations);
		}

		private JTesterExpectations expectations;

		public ICallMethod call;

		public IWillReturn returns;

		public void throwException(Throwable throwable) {
			expectations.will(Expectations.throwException(throwable));
		}

		public void doAll(Action... actions) {
			expectations.will(Expectations.doAll(actions));
		}

		public void onConsecutiveCalls(Action... actions) {
			expectations.will(Expectations.onConsecutiveCalls(actions));
		}
	}
}
