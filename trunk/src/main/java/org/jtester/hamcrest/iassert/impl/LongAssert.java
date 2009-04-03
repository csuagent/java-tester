package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.object.ILongAssert;

public class LongAssert extends NumberAssert<Long, ILongAssert> implements
		ILongAssert {
	public LongAssert() {
		super(ILongAssert.class);
	}

	public LongAssert(Long l) {
		super(l, ILongAssert.class);
	}
}
