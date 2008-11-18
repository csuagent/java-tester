package org.jtester.unitils.jmock.fluent.impl;

import org.jtester.unitils.jmock.fluent.IHamcrestAssert;
import org.jtester.unitils.jmock.fluent.IStringAssert;

public class HamcrestAssert implements IHamcrestAssert {

	public IStringAssert want(String value) {
		return new StringAssert(value, StringAssert.class);
	}
}
