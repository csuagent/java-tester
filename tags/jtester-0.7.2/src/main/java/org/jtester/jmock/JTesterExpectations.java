package org.jtester.jmock;

import java.util.Collection;

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
			this.returns = new ReturnValue(expectations);
		}

		private JTesterExpectations expectations;

		public ICallMethod call;

		public ReturnValue returns;

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

	public static class ReturnValue {
		private Expectations expectations;

		public ReturnValue(Expectations expectations) {
			this.expectations = expectations;
		}

		public void value(Object result) {
			expectations.will(Expectations.returnValue(result));
		}

		public void iterator(Collection<?> collection) {
			expectations.will(Expectations.returnIterator(collection));
		}

		public <T> void iterator(T... items) {
			expectations.will(Expectations.returnIterator(items));
		}

		public void enumeration(Collection<?> collection) {
			expectations.will(Expectations.returnEnumeration(collection));
		}

		public <T> void enumeration(T... items) {
			expectations.will(Expectations.returnEnumeration(items));
		}
	}
}
