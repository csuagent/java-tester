package org.jtester.unitils.jmock.fluent.impl;

import org.jtester.unitils.jmock.fluent.IAssert;
import org.jtester.unitils.jmock.fluent.IIntegerAssert;

public class IntegerAssert extends BaseAssert<Integer, IIntegerAssert> implements IIntegerAssert {

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
