package org.jtester.hamcrest.iassert.impl;

import org.jtester.hamcrest.iassert.IDoubleAssert;

public class DoubleAssert extends NumberAssert<Double, IDoubleAssert> implements
		IDoubleAssert {
	public DoubleAssert() {
		super(IDoubleAssert.class);
	}

	public DoubleAssert(Double dbl) {
		super(dbl, IDoubleAssert.class);
	}
}
