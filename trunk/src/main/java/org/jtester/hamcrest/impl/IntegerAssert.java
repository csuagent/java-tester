package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.IIntegerAssert;

public class IntegerAssert extends NumberAssert<Integer, IIntegerAssert> implements IIntegerAssert {

	public IntegerAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public IntegerAssert(Class<Integer> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

	public IntegerAssert(Integer value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}
}
