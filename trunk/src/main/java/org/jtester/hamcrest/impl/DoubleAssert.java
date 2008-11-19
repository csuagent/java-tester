package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.IDoubleAssert;

public class DoubleAssert extends NumberAssert<Double, IDoubleAssert> implements IDoubleAssert {

	public DoubleAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public DoubleAssert(Class<Double> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public DoubleAssert(Double value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

}
