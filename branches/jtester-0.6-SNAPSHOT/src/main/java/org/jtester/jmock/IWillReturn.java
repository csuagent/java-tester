package org.jtester.jmock;

import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.api.Action;

public class IWillReturn {
	private Expectations will;

	public IWillReturn(Expectations will) {
		this.will = will;
	}

	public void returnValue(Object result) {
		will.will(Expectations.returnValue(result));
	}

	public void throwException(Throwable throwable) {
		will.will(Expectations.throwException(throwable));
	}

	public void returnIterator(Collection<?> collection) {
		will.will(Expectations.returnIterator(collection));
	}

	public <T> void returnIterator(T... items) {
		will.will(Expectations.returnIterator(items));
	}

	public void returnEnumeration(Collection<?> collection) {
		will.will(Expectations.returnEnumeration(collection));
	}

	public <T> void returnEnumeration(T... items) {
		will.will(Expectations.returnEnumeration(items));
	}

	public void doAll(Action... actions) {
		will.will(Expectations.doAll(actions));
	}

	public void onConsecutiveCalls(Action... actions) {
		will.will(Expectations.onConsecutiveCalls(actions));
	}
}
