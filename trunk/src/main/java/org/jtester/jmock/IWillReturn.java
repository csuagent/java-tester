package org.jtester.jmock;

import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.api.Action;

public class IWillReturn {
	private Expectations $;

	public IWillReturn(Expectations $) {
		this.$ = $;
	}

	public void returnValue(Object result) {
		$.will(Expectations.returnValue(result));
	}

	public void throwException(Throwable throwable) {
		$.will(Expectations.throwException(throwable));
	}

	public void returnIterator(Collection<?> collection) {
		$.will(Expectations.returnIterator(collection));
	}

	public <T> void returnIterator(T... items) {
		$.will(Expectations.returnIterator(items));
	}

	public void returnEnumeration(Collection<?> collection) {
		$.will(Expectations.returnEnumeration(collection));
	}

	public <T> void returnEnumeration(T... items) {
		$.will(Expectations.returnEnumeration(items));
	}

	public void doAll(Action... actions) {
		$.will(Expectations.doAll(actions));
	}

	public void onConsecutiveCalls(Action... actions) {
		$.will(Expectations.onConsecutiveCalls(actions));
	}
}
