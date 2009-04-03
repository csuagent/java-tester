package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.object.IIntegerAssert;

public class IntegerAssert extends NumberAssert<Integer, IIntegerAssert>
		implements IIntegerAssert {
	public IntegerAssert() {
		super(IIntegerAssert.class);
	}

	public IntegerAssert(Integer i) {
		super(i, IIntegerAssert.class);
	}
}
