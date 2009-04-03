package org.jtester.hamcrest.iassert.object.impl;

import org.jtester.hamcrest.iassert.object.IDoubleAssert;

public class DoubleAssert extends NumberAssert<Double, IDoubleAssert> implements IDoubleAssert {
	public DoubleAssert() {
		super(IDoubleAssert.class);
	}

	public DoubleAssert(Double dbl) {
		super(dbl, IDoubleAssert.class);
	}
}
