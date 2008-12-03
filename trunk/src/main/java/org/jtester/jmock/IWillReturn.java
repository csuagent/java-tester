package org.jtester.jmock;

import org.jmock.Expectations;

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
}
