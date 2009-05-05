package org.jtester.jmock;

import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.api.Action;

public class IWillReturn {
	private Expectations expectations;

	public IWillReturn(Expectations expectations) {
		this.expectations = expectations;
	}

	public void value(Object result) {
		expectations.will(Expectations.returnValue(result));
	}

	public void exception(Throwable throwable) {
		expectations.will(Expectations.throwException(throwable));
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

	public void doAll(Action... actions) {
		expectations.will(Expectations.doAll(actions));
	}

	public void onConsecutiveCalls(Action... actions) {
		expectations.will(Expectations.onConsecutiveCalls(actions));
	}
}
