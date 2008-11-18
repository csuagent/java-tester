package org.jtester.unitils.jmock.fluent.impl;

import org.jtester.unitils.jmock.fluent.IStringAssert;

public class ExpectAssertThat {
	public IStringAssert string;

	public ExpectAssertThat() {
		string = new StringAssert(IStringAssert.class);
	}
}
